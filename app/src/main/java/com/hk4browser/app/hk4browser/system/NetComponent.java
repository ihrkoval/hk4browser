package com.hk4browser.app.hk4browser.system;



import com.hk4browser.app.hk4browser.MainActivity;
import com.hk4browser.app.hk4browser.fragments.BoardFragment;
import com.hk4browser.app.hk4browser.fragments.PostFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={NetModule.class})
public interface NetComponent {
   void inject(MainActivity activity);
   void inject(BoardFragment fragment);
   void inject(PostFragment fragment);
}