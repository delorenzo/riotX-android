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
package im.vector.matrix.android.internal.session.sync.model

import com.squareup.moshi.JsonClass


/**
 * This class describes the device information
 */
@JsonClass(generateAdapter = true)
internal data class DeviceInfo(
        /**
         * The owner user id
         */
        val user_id: String? = null,

        /**
         * The device id
         */
        val device_id: String? = null,

        /**
         * The device display name
         */
        val display_name: String? = null,

        /**
         * The last time this device has been seen.
         */
        val last_seen_ts: Long = 0,

        /**
         * The last ip address
         */
        val last_seen_ip: String? = null

)
