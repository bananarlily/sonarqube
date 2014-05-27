/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.sonar.core.qualityprofile.db;

import org.apache.ibatis.session.SqlSession;
import org.sonar.api.ServerComponent;
import org.sonar.core.persistence.MyBatis;

import java.util.List;

public class ActiveRuleDao implements ServerComponent {

  private final MyBatis mybatis;

  public ActiveRuleDao(MyBatis mybatis) {
    this.mybatis = mybatis;
  }

  public void insert(ActiveRuleDto dto, SqlSession session) {
    session.getMapper(ActiveRuleMapper.class).insert(dto);
  }

  public void insert(ActiveRuleDto dto) {
    SqlSession session = mybatis.openSession(false);
    try {
      insert(dto, session);
      session.commit();
    } finally {
      MyBatis.closeQuietly(session);
    }
  }

  public List<ActiveRuleDto> selectByProfileId(int profileId) {
    SqlSession session = mybatis.openSession(false);
    try {
      return session.getMapper(ActiveRuleMapper.class).selectByProfileId(profileId);
    } finally {
      MyBatis.closeQuietly(session);
    }
  }

  public void insert(ActiveRuleParamDto dto, SqlSession session) {
    session.getMapper(ActiveRuleMapper.class).insertParameter(dto);
  }

  public void insert(ActiveRuleParamDto dto) {
    SqlSession session = mybatis.openSession(false);
    try {
      insert(dto, session);
      session.commit();
    } finally {
      MyBatis.closeQuietly(session);
    }
  }

  public List<ActiveRuleParamDto> selectParamsByProfileId(int profileId) {
    SqlSession session = mybatis.openSession(false);
    try {
      return session.getMapper(ActiveRuleMapper.class).selectParamsByProfileId(profileId);
    } finally {
      MyBatis.closeQuietly(session);
    }
  }
}
