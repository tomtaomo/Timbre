package voice.features.playbackScreen.view

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import voice.core.strings.R
import voice.core.ui.icons.VoiceIcons
import voice.features.playbackScreen.BookPlayViewState

@Composable
internal fun BookPlayAppBar(
  viewState: BookPlayViewState,
  onSpeedChangeClick: () -> Unit,
  onSkipSilenceClick: () -> Unit,
  onVolumeBoostClick: () -> Unit,
  onSkipIntroOutroClick: () -> Unit,
  onCloseClick: () -> Unit,
  useLandscapeLayout: Boolean,
) {
  val appBarActions: @Composable RowScope.() -> Unit = {
    IconButton(onClick = onSpeedChangeClick) {
      Icon(
        imageVector = VoiceIcons.Speed,
        contentDescription = stringResource(id = R.string.playback_speed_title),
      )
    }
    OverflowMenu(
      skipSilence = viewState.skipSilence,
      onSkipSilenceClick = onSkipSilenceClick,
      onVolumeBoostClick = onVolumeBoostClick,
      onSkipIntroOutroClick = onSkipIntroOutroClick,
    )
  }
  if (useLandscapeLayout) {
    TopAppBar(
      navigationIcon = {
        CloseIcon(onCloseClick)
      },
      actions = appBarActions,
      title = {
        AppBarTitle(viewState.title)
      },
    )
  } else {
    LargeTopAppBar(
      navigationIcon = {
        CloseIcon(onCloseClick)
      },
      actions = appBarActions,
      title = {
        AppBarTitle(viewState.title)
      },
    )
  }
}
