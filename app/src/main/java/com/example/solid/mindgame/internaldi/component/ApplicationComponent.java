/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.solid.mindgame.internaldi.component;

import android.content.Context;

import com.example.solid.mindgame.base.BaseActivity;
import com.example.solid.mindgame.internaldi.modules.ApplicationModule;
import com.example.solid.mindgame.internaldi.modules.GameModule;
import com.example.solid.mindgame.internaldi.modules.NetModule;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.DefaultScore;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.DiagonalScore;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.LocalGameManager;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.OnlineGameManager;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket.WebSocketContract;

import javax.inject.Singleton;
import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class, NetModule.class, GameModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);
    Context context();
    WebSocketContract webSocketContract();
    LocalGameManager localGameManager();
    OnlineGameManager onlineGameManager();
    DiagonalScore diagonalScore();
    DefaultScore defaultScore();

}
