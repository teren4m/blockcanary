/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.blockcanary

import com.github.moduth.blockcanary.BlockCanaryContext

class AppBlockCanaryContext : BlockCanaryContext() {

    override fun getQualifier() = "${BuildConfig.VERSION_CODE}_${BuildConfig.VERSION_NAME}_YYB"

    override fun getUid() = "87224330"

    override fun getNetworkType() = "4G"

    override fun getConfigDuration() = 9999

    override fun getConfigBlockThreshold() = 50

    override fun isNeedDisplay() = BuildConfig.DEBUG
}