/*
 * Copyright 2019 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.wtomatrix.core.di

import android.content.Context
import android.content.res.Resources
import dagger.BindsInstance
import dagger.Component
import im.vector.wtomatrix.ActiveSessionDataSource
import im.vector.wtomatrix.EmojiCompatFontProvider
import im.vector.wtomatrix.EmojiCompatWrapper
import im.vector.wtomatrix.VectorApplication
import im.vector.wtomatrix.core.dialogs.UnrecognizedCertificateDialog
import im.vector.wtomatrix.core.error.ErrorFormatter
import im.vector.wtomatrix.core.pushers.PushersManager
import im.vector.wtomatrix.core.utils.AssetReader
import im.vector.wtomatrix.core.utils.DimensionConverter
import im.vector.wtomatrix.features.call.webrtc.WebRtcCallManager
import im.vector.wtomatrix.features.configuration.VectorConfiguration
import im.vector.wtomatrix.features.crypto.keysrequest.KeyRequestHandler
import im.vector.wtomatrix.features.crypto.verification.IncomingVerificationRequestHandler
import im.vector.wtomatrix.features.grouplist.SelectedGroupDataSource
import im.vector.wtomatrix.features.home.AvatarRenderer
import im.vector.wtomatrix.features.home.HomeRoomListDataSource
import im.vector.wtomatrix.features.home.room.detail.RoomDetailPendingActionStore
import im.vector.wtomatrix.features.home.room.detail.timeline.helper.MatrixItemColorProvider
import im.vector.wtomatrix.features.home.room.detail.timeline.helper.RoomSummariesHolder
import im.vector.wtomatrix.features.html.EventHtmlRenderer
import im.vector.wtomatrix.features.html.VectorHtmlCompressor
import im.vector.wtomatrix.features.login.ReAuthHelper
import im.vector.wtomatrix.features.navigation.Navigator
import im.vector.wtomatrix.features.notifications.NotifiableEventResolver
import im.vector.wtomatrix.features.notifications.NotificationBroadcastReceiver
import im.vector.wtomatrix.features.notifications.NotificationDrawerManager
import im.vector.wtomatrix.features.notifications.NotificationUtils
import im.vector.wtomatrix.features.notifications.PushRuleTriggerListener
import im.vector.wtomatrix.features.pin.PinCodeStore
import im.vector.wtomatrix.features.pin.PinLocker
import im.vector.wtomatrix.features.popup.PopupAlertManager
import im.vector.wtomatrix.features.rageshake.BugReporter
import im.vector.wtomatrix.features.rageshake.VectorFileLogger
import im.vector.wtomatrix.features.rageshake.VectorUncaughtExceptionHandler
import im.vector.wtomatrix.features.reactions.data.EmojiDataSource
import im.vector.wtomatrix.features.session.SessionListener
import im.vector.wtomatrix.features.settings.VectorPreferences
import im.vector.wtomatrix.features.ui.UiStateRepository
import org.matrix.android.sdk.api.Matrix
import org.matrix.android.sdk.api.auth.AuthenticationService
import org.matrix.android.sdk.api.auth.HomeServerHistoryService
import org.matrix.android.sdk.api.raw.RawService
import org.matrix.android.sdk.api.session.Session
import javax.inject.Singleton

@Component(modules = [VectorModule::class])
@Singleton
interface VectorComponent {

    fun inject(notificationBroadcastReceiver: NotificationBroadcastReceiver)

    fun inject(vectorApplication: VectorApplication)

    fun matrix(): Matrix

    fun matrixItemColorProvider(): MatrixItemColorProvider

    fun sessionListener(): SessionListener

    fun currentSession(): Session

    fun notificationUtils(): NotificationUtils

    fun notificationDrawerManager(): NotificationDrawerManager

    fun appContext(): Context

    fun resources(): Resources

    fun assetReader(): AssetReader

    fun dimensionConverter(): DimensionConverter

    fun vectorConfiguration(): VectorConfiguration

    fun avatarRenderer(): AvatarRenderer

    fun activeSessionHolder(): ActiveSessionHolder

    fun unrecognizedCertificateDialog(): UnrecognizedCertificateDialog

    fun emojiCompatFontProvider(): EmojiCompatFontProvider

    fun emojiCompatWrapper(): EmojiCompatWrapper

    fun eventHtmlRenderer(): EventHtmlRenderer

    fun vectorHtmlCompressor(): VectorHtmlCompressor

    fun navigator(): Navigator

    fun errorFormatter(): ErrorFormatter

    fun homeRoomListObservableStore(): HomeRoomListDataSource

    fun selectedGroupStore(): SelectedGroupDataSource

    fun roomDetailPendingActionStore(): RoomDetailPendingActionStore

    fun activeSessionObservableStore(): ActiveSessionDataSource

    fun incomingVerificationRequestHandler(): IncomingVerificationRequestHandler

    fun incomingKeyRequestHandler(): KeyRequestHandler

    fun authenticationService(): AuthenticationService

    fun rawService(): RawService

    fun homeServerHistoryService(): HomeServerHistoryService

    fun bugReporter(): BugReporter

    fun vectorUncaughtExceptionHandler(): VectorUncaughtExceptionHandler

    fun pushRuleTriggerListener(): PushRuleTriggerListener

    fun pusherManager(): PushersManager

    fun notifiableEventResolver(): NotifiableEventResolver

    fun vectorPreferences(): VectorPreferences

    fun vectorFileLogger(): VectorFileLogger

    fun uiStateRepository(): UiStateRepository

    fun pinCodeStore(): PinCodeStore

    fun emojiDataSource(): EmojiDataSource

    fun alertManager(): PopupAlertManager

    fun reAuthHelper(): ReAuthHelper

    fun pinLocker(): PinLocker

    fun webRtcCallManager(): WebRtcCallManager

    fun roomSummaryHolder(): RoomSummariesHolder

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): VectorComponent
    }
}
