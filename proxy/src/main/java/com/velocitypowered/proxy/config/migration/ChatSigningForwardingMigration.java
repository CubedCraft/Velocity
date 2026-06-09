/*
 * Copyright (C) 2026 Velocity Contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.velocitypowered.proxy.config.migration;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import org.apache.logging.log4j.Logger;

/**
 * Configuration migration for the "forward-chat-signing" option.
 */
public final class ChatSigningForwardingMigration implements ConfigurationMigration {

  @Override
  public boolean shouldMigrate(CommentedFileConfig config) {
    return configVersion(config) < 2.9;
  }

  @Override
  public void migrate(CommentedFileConfig config, Logger logger) {
    config.set("forward-chat-signing", config.getOrElse("forward-chat-signing", true));
    config.setComment("forward-chat-signing", """
        Should the proxy forward player chat signing data to backend servers? Disable this to make
        backend servers treat player chat and commands as unsigned when enforce-secure-profile=false.""");
    config.set("config-version", "2.9");
  }
}
