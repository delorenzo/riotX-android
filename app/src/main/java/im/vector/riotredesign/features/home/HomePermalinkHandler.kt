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

package im.vector.riotredesign.features.home

import android.net.Uri
import im.vector.matrix.android.api.permalinks.PermalinkData
import im.vector.matrix.android.api.permalinks.PermalinkParser

class HomePermalinkHandler(private val navigator: HomeNavigator) {

    fun launch(deepLink: String?) {
        val uri = deepLink?.let { Uri.parse(it) }
        launch(uri)
    }

    fun launch(deepLink: Uri?) {
        if (deepLink == null) {
            return
        }
        val permalinkData = PermalinkParser.parse(deepLink)
        when (permalinkData) {
            is PermalinkData.EventLink -> {
                navigator.openRoomDetail(permalinkData.roomIdOrAlias, permalinkData.eventId, true)
            }
            is PermalinkData.RoomLink -> {
                navigator.openRoomDetail(permalinkData.roomIdOrAlias, null, true)
            }
            is PermalinkData.GroupLink -> {
                navigator.openGroupDetail(permalinkData.groupId)
            }
            is PermalinkData.UserLink -> {
                navigator.openUserDetail(permalinkData.userId)
            }
            is PermalinkData.FallbackLink -> {

            }
        }
    }

}