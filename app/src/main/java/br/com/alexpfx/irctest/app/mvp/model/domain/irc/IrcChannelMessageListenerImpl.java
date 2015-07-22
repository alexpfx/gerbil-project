package br.com.alexpfx.irctest.app.mvp.model.domain.irc;

import android.util.Log;
import com.ircclouds.irc.api.domain.messages.*;
import com.ircclouds.irc.api.domain.messages.interfaces.IMessage;

import static br.com.alexpfx.irctest.app.utils.TagUtils.method;
import static br.com.alexpfx.irctest.app.utils.TagUtils.tag;

/**
 * Created by alexandre on 22/07/15.
 */
public class IrcChannelMessageListenerImpl implements IrcChannelMessageListener {

    private MessageFilter dispatcher;

    @Override
    public void onChannelMessage(ChannelPrivMsg aMsg) {
        if (dispatcher == null) {
            return;
        }
        dispatcher.rawMessage(aMsg.getChannelName(), aMsg.getSource().getNick(), aMsg.getText());
    }

    @Override
    public void onChannelJoin(ChanJoinMessage aMsg) {
        final StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[0];
        Log.d(tag(), method());
    }

    @Override
    public void onChannelPart(ChanPartMessage aMsg) {
        Log.d(tag(), method());
    }

    @Override
    public void onChannelNotice(ChannelNotice aMsg) {
        Log.d(tag(this), "onChannelNotice");
    }

    @Override
    public void onChannelAction(ChannelActionMsg aMsg) {
        Log.d(tag(this), "onChannelAction");

    }

    @Override
    public void onChannelKick(ChannelKick aMsg) {
        Log.d(tag(), method());
    }

    @Override
    public void onTopicChange(TopicMessage aMsg) {
        Log.d(tag(this), "onTopicChange");
    }

    @Override
    public void onUserPrivMessage(UserPrivMsg aMsg) {
        Log.d(tag(), method());
    }

    @Override
    public void onUserNotice(UserNotice aMsg) {
        Log.d(tag(this), "onUserNotice");
    }

    @Override
    public void onUserAction(UserActionMsg aMsg) {
        Log.d(tag(this), "onUserAction");
    }

    @Override
    public void onServerNumericMessage(ServerNumericMessage aMsg) {
        Log.d(tag(this), "onServerNumericMessage");
    }

    @Override
    public void onServerNotice(ServerNotice aMsg) {
        Log.d(tag(this), "onServerNotice");
    }

    @Override
    public void onNickChange(NickMessage aMsg) {
        Log.d(tag(this), "onNickChange");
    }

    @Override
    public void onUserQuit(QuitMessage aMsg) {
        Log.d(tag(this), "onUserQuit");
    }

    @Override
    public void onError(ErrorMessage aMsg) {
        Log.d(tag(this), "onError");
    }

    @Override
    public void onChannelMode(ChannelModeMessage aMsg) {
        Log.d(tag(this), "onChannelMode");
    }

    @Override
    public void onUserPing(UserPing aMsg) {
        Log.d(tag(this), "onUserPing");
    }

    @Override
    public void onUserVersion(UserVersion aMsg) {
        Log.d(tag(this), "onUserVersion");
    }

    @Override
    public void onServerPing(ServerPing aMsg) {
        Log.d(tag(this), "onServerPing");
    }

    @Override
    public void onMessage(IMessage aMessage) {
        Log.d(tag(this), "onMessage");
    }

    @Override
    public void setMessageFilter(MessageFilter dispatcher) {
        this.dispatcher = dispatcher;
    }
}
