package ng.devtamuno.unsplash.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import ng.devtamuno.unsplash.views.common.MessageDialogManager
import ng.devtamuno.unsplash.views.common.ProgressDialogManager

@InstallIn(FragmentComponent::class)
@Module
object FragmentModule {

    @Provides
    fun providesProgressDialogManager(@ActivityContext context: Context) =
        ProgressDialogManager(context)

    @Provides
    fun providesMessageDialogManager(@ActivityContext context: Context) =
        MessageDialogManager(context)
}