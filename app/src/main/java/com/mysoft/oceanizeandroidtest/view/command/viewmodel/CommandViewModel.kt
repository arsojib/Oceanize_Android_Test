package com.mysoft.oceanizeandroidtest.view.command.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysoft.oceanizeandroidtest.R
import com.mysoft.oceanizeandroidtest.data.model.CommandInfo
import com.mysoft.oceanizeandroidtest.data.model.Resource
import com.mysoft.oceanizeandroidtest.data.rest.Repository
import com.mysoft.oceanizeandroidtest.di.scope.CommandScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.sshd.client.SshClient
import org.apache.sshd.client.channel.ClientChannelEvent
import org.apache.sshd.client.session.ClientSession
import org.apache.sshd.common.channel.Channel
import org.apache.sshd.server.forward.AcceptAllForwardingFilter
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@CommandScope
class CommandViewModel @Inject constructor(
    private val applicationContext: Context,
    private val repository: Repository
) : ViewModel() {

    private val commandList = MutableLiveData<Resource<List<CommandInfo>>>()
    val commandOutput = MutableLiveData<String>()

    init {
        commandOutput.value = applicationContext.getString(R.string.no_command_executed_yet)
        getCommandList()
    }

    private fun getCommandList() {
        viewModelScope.launch {
            commandList.value = repository.getCommands()
        }
    }

    fun executeSSHCommand(commandInfo: CommandInfo) {
        commandOutput.postValue(applicationContext.getString(R.string.executing))

        // Setting user.com property manually
        // since isn't set by default in android
        val key = "user.home"
        val value: String = applicationContext.applicationInfo.dataDir
        System.setProperty(key, value)

        // Creating a client instance
        val client: SshClient = SshClient.setUpDefaultClient()
        client.forwardingFilter = AcceptAllForwardingFilter.INSTANCE
        client.start()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                try {
                    client.connect(commandInfo.username, commandInfo.host, commandInfo.port)
                        .verify(10000)
                        .session
                        .use { session: ClientSession ->
                            session.addPasswordIdentity(commandInfo.password)
                            session.auth().verify(50000)
                            println("Connection establihed")

                            // Create a channel to communicate
                            val channel = session.createChannel(Channel.CHANNEL_SHELL)
                            println("Starting shell")
                            val responseStream = ByteArrayOutputStream()
                            channel.setOut(responseStream)

                            // Open channel
                            channel.open().verify(5, TimeUnit.SECONDS)
                            channel.invertedIn.use { pipedIn: OutputStream ->
                                pipedIn.write("commandInfo.command".toByteArray())
                                pipedIn.flush()
                            }

                            // Close channel
                            channel.waitFor(
                                EnumSet.of(ClientChannelEvent.CLOSED),
                                TimeUnit.SECONDS.toMillis(5)
                            )

                            // Output after converting to string type
                            val responseString = String(responseStream.toByteArray())
                            println(responseString)
                            commandOutput.postValue(responseString)
                        }
                } catch (e: IOException) {
                    e.printStackTrace()
                    commandOutput.postValue(e.message)
                } finally {
                    client.stop()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                commandOutput.postValue(e.message)
            }
        }
    }

    fun commandList() = commandList
}