package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases;

import br.com.alexpfx.android.lib.base.mvpbase.executor.Interactor;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.UserIdentity;

import java.util.List;

/**
 * Created by alexandre on 07/07/15.
 */
public interface NotifyUsersUseCase extends Interactor {

    void execute(List<UserIdentity> userList, Callback callback);

    interface Callback {

    }

}
