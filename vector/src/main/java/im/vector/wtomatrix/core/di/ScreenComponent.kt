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

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import im.vector.wtomatrix.core.dialogs.UnrecognizedCertificateDialog
import im.vector.wtomatrix.core.error.ErrorFormatter
import im.vector.wtomatrix.core.preference.UserAvatarPreference
import im.vector.wtomatrix.features.MainActivity
import im.vector.wtomatrix.features.auth.ReAuthActivity
import im.vector.wtomatrix.features.call.CallControlsBottomSheet
import im.vector.wtomatrix.features.call.VectorCallActivity
import im.vector.wtomatrix.features.call.conference.VectorJitsiActivity
import im.vector.wtomatrix.features.call.transfer.CallTransferActivity
import im.vector.wtomatrix.features.createdirect.CreateDirectRoomActivity
import im.vector.wtomatrix.features.crypto.keysbackup.settings.KeysBackupManageActivity
import im.vector.wtomatrix.features.crypto.quads.SharedSecureStorageActivity
import im.vector.wtomatrix.features.crypto.recover.BootstrapBottomSheet
import im.vector.wtomatrix.features.crypto.verification.VerificationBottomSheet
import im.vector.wtomatrix.features.debug.DebugMenuActivity
import im.vector.wtomatrix.features.devtools.RoomDevToolActivity
import im.vector.wtomatrix.features.home.HomeActivity
import im.vector.wtomatrix.features.home.HomeModule
import im.vector.wtomatrix.features.home.room.detail.RoomDetailActivity
import im.vector.wtomatrix.features.home.room.detail.readreceipts.DisplayReadReceiptsBottomSheet
import im.vector.wtomatrix.features.home.room.detail.search.SearchActivity
import im.vector.wtomatrix.features.home.room.detail.timeline.action.MessageActionsBottomSheet
import im.vector.wtomatrix.features.home.room.detail.timeline.edithistory.ViewEditHistoryBottomSheet
import im.vector.wtomatrix.features.home.room.detail.timeline.reactions.ViewReactionsBottomSheet
import im.vector.wtomatrix.features.home.room.detail.widget.RoomWidgetsBottomSheet
import im.vector.wtomatrix.features.home.room.filtered.FilteredRoomsActivity
import im.vector.wtomatrix.features.home.room.list.RoomListModule
import im.vector.wtomatrix.features.home.room.list.actions.RoomListQuickActionsBottomSheet
import im.vector.wtomatrix.features.invite.InviteUsersToRoomActivity
import im.vector.wtomatrix.features.invite.VectorInviteView
import im.vector.wtomatrix.features.link.LinkHandlerActivity
import im.vector.wtomatrix.features.login.LoginActivity
import im.vector.wtomatrix.features.matrixto.MatrixToBottomSheet
import im.vector.wtomatrix.features.media.BigImageViewerActivity
import im.vector.wtomatrix.features.media.VectorAttachmentViewerActivity
import im.vector.wtomatrix.features.navigation.Navigator
import im.vector.wtomatrix.features.permalink.PermalinkHandlerActivity
import im.vector.wtomatrix.features.pin.PinLocker
import im.vector.wtomatrix.features.qrcode.QrCodeScannerActivity
import im.vector.wtomatrix.features.rageshake.BugReportActivity
import im.vector.wtomatrix.features.rageshake.BugReporter
import im.vector.wtomatrix.features.rageshake.RageShake
import im.vector.wtomatrix.features.reactions.EmojiReactionPickerActivity
import im.vector.wtomatrix.features.reactions.widget.ReactionButton
import im.vector.wtomatrix.features.roomdirectory.RoomDirectoryActivity
import im.vector.wtomatrix.features.roomdirectory.createroom.CreateRoomActivity
import im.vector.wtomatrix.features.roommemberprofile.RoomMemberProfileActivity
import im.vector.wtomatrix.features.roommemberprofile.devices.DeviceListBottomSheet
import im.vector.wtomatrix.features.roomprofile.RoomProfileActivity
import im.vector.wtomatrix.features.roomprofile.alias.detail.RoomAliasBottomSheet
import im.vector.wtomatrix.features.roomprofile.settings.historyvisibility.RoomHistoryVisibilityBottomSheet
import im.vector.wtomatrix.features.roomprofile.settings.joinrule.RoomJoinRuleBottomSheet
import im.vector.wtomatrix.features.settings.VectorSettingsActivity
import im.vector.wtomatrix.features.settings.devices.DeviceVerificationInfoBottomSheet
import im.vector.wtomatrix.features.share.IncomingShareActivity
import im.vector.wtomatrix.features.signout.soft.SoftLogoutActivity
import im.vector.wtomatrix.features.terms.ReviewTermsActivity
import im.vector.wtomatrix.features.ui.UiStateRepository
import im.vector.wtomatrix.features.usercode.UserCodeActivity
import im.vector.wtomatrix.features.widgets.WidgetActivity
import im.vector.wtomatrix.features.widgets.permissions.RoomWidgetPermissionBottomSheet
import im.vector.wtomatrix.features.workers.signout.SignOutBottomSheetDialogFragment

@Component(
        dependencies = [
            VectorComponent::class
        ],
        modules = [
            ViewModelModule::class,
            FragmentModule::class,
            HomeModule::class,
            RoomListModule::class,
            ScreenModule::class
        ]
)
@ScreenScope
interface ScreenComponent {

    /* ==========================================================================================
     * Shortcut to VectorComponent elements
     * ========================================================================================== */

    fun activeSessionHolder(): ActiveSessionHolder
    fun fragmentFactory(): FragmentFactory
    fun viewModelFactory(): ViewModelProvider.Factory
    fun bugReporter(): BugReporter
    fun rageShake(): RageShake
    fun navigator(): Navigator
    fun pinLocker(): PinLocker
    fun errorFormatter(): ErrorFormatter
    fun uiStateRepository(): UiStateRepository
    fun unrecognizedCertificateDialog(): UnrecognizedCertificateDialog

    /* ==========================================================================================
     * Activities
     * ========================================================================================== */

    fun inject(activity: HomeActivity)
    fun inject(activity: RoomDetailActivity)
    fun inject(activity: RoomProfileActivity)
    fun inject(activity: RoomMemberProfileActivity)
    fun inject(activity: VectorSettingsActivity)
    fun inject(activity: KeysBackupManageActivity)
    fun inject(activity: EmojiReactionPickerActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: LinkHandlerActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: RoomDirectoryActivity)
    fun inject(activity: BugReportActivity)
    fun inject(activity: FilteredRoomsActivity)
    fun inject(activity: CreateRoomActivity)
    fun inject(activity: CreateDirectRoomActivity)
    fun inject(activity: IncomingShareActivity)
    fun inject(activity: SoftLogoutActivity)
    fun inject(activity: PermalinkHandlerActivity)
    fun inject(activity: QrCodeScannerActivity)
    fun inject(activity: DebugMenuActivity)
    fun inject(activity: SharedSecureStorageActivity)
    fun inject(activity: BigImageViewerActivity)
    fun inject(activity: InviteUsersToRoomActivity)
    fun inject(activity: ReviewTermsActivity)
    fun inject(activity: WidgetActivity)
    fun inject(activity: VectorCallActivity)
    fun inject(activity: VectorAttachmentViewerActivity)
    fun inject(activity: VectorJitsiActivity)
    fun inject(activity: SearchActivity)
    fun inject(activity: UserCodeActivity)
    fun inject(activity: CallTransferActivity)
    fun inject(activity: ReAuthActivity)
    fun inject(activity: RoomDevToolActivity)

    /* ==========================================================================================
     * BottomSheets
     * ========================================================================================== */

    fun inject(bottomSheet: MessageActionsBottomSheet)
    fun inject(bottomSheet: ViewReactionsBottomSheet)
    fun inject(bottomSheet: ViewEditHistoryBottomSheet)
    fun inject(bottomSheet: DisplayReadReceiptsBottomSheet)
    fun inject(bottomSheet: RoomListQuickActionsBottomSheet)
    fun inject(bottomSheet: RoomAliasBottomSheet)
    fun inject(bottomSheet: RoomHistoryVisibilityBottomSheet)
    fun inject(bottomSheet: RoomJoinRuleBottomSheet)
    fun inject(bottomSheet: VerificationBottomSheet)
    fun inject(bottomSheet: DeviceVerificationInfoBottomSheet)
    fun inject(bottomSheet: DeviceListBottomSheet)
    fun inject(bottomSheet: BootstrapBottomSheet)
    fun inject(bottomSheet: RoomWidgetPermissionBottomSheet)
    fun inject(bottomSheet: RoomWidgetsBottomSheet)
    fun inject(bottomSheet: CallControlsBottomSheet)
    fun inject(bottomSheet: SignOutBottomSheetDialogFragment)
    fun inject(bottomSheet: MatrixToBottomSheet)

    /* ==========================================================================================
     * Others
     * ========================================================================================== */

    fun inject(view: VectorInviteView)
    fun inject(preference: UserAvatarPreference)
    fun inject(button: ReactionButton)

    /* ==========================================================================================
     * Factory
     * ========================================================================================== */

    @Component.Factory
    interface Factory {
        fun create(vectorComponent: VectorComponent,
                   @BindsInstance context: AppCompatActivity
        ): ScreenComponent
    }
}
