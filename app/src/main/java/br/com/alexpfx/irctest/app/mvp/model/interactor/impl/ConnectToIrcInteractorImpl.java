package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import android.util.Log;
import br.com.alexpfx.irctest.app.irc.IRCApiSingleton;
import br.com.alexpfx.irctest.app.irc.IRCServiceUtils;
import br.com.alexpfx.irctest.app.mvp.model.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentify;
import br.com.alexpfx.irctest.app.mvp.model.interactor.ConnectToIrcInteractor;
import br.com.alexpfx.irctest.app.mvp.model.interactor.Interactor;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.MainThread;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.MainThreadImpl;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.ThreadExecutor;
import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.state.IIRCState;

/**
 * Created by alexandre on 05/07/15.
 */
public class ConnectToIrcInteractorImpl implements Interactor, ConnectToIrcInteractor {

    private IRCApi ircApi = IRCApiSingleton.INSTANCE.get();

    //TODO: injectar.
    private MainThread mainThread = MainThreadImpl.MainThreadSingleton.INSTANCE.get();

    //TODO: injectar.
    private ThreadExecutor executor = ThreadExecutor.ThreadExecutorSingleton.INSTANCE.get();

    private UserIdentify userIdentity;
    private ServerIdentity serverIdentity;
    private Callback callback;
    private String tag = ConnectToIrcInteractorImpl.class.getSimpleName();

    public ConnectToIrcInteractorImpl() {
    }

    @Override
    public void execute(UserIdentify userIdentity, ServerIdentity serverIdentity, Callback callback) {
        this.userIdentity = userIdentity;
        this.serverIdentity = serverIdentity;
        this.callback = callback;
        Log.d(tag, "executing connection");
        executor.run(this);
    }

    @Override
    public void run() {
        ircApi.connect(IRCServiceUtils
                .getServerParameters(serverIdentity.getIrcServer(), userIdentity.getEmail(), userIdentity
                        .getName(), userIdentity.getNickname(), userIdentity
                        .getAlternative()), new com.ircclouds.irc.api.Callback<IIRCState>() {
            @Override
            public void onSuccess(IIRCState aObject) {
                notifySucess();
            }

            @Override
            public void onFailure(Exception aExc) {
                notifyFailure(aExc);
            }
        });

    }

    private void notifyFailure(final Exception aExc) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(aExc);
            }
        });

    }

    private void notifySucess() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess();
            }
        });

    }

}
