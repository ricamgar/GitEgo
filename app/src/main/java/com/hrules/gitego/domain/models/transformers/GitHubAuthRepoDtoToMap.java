/*
 * Copyright (c) 2016. Héctor de Isidro - hrules6872
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hrules.gitego.domain.models.transformers;

import android.support.annotation.NonNull;
import com.hrules.gitego.data.persistence.database.DatabaseConstants;
import com.hrules.gitego.domain.models.GitHubAuthRepoDto;
import com.hrules.gitego.domain.models.transformers.base.Transformer;
import java.util.HashMap;
import java.util.Map;

public class GitHubAuthRepoDtoToMap implements Transformer<GitHubAuthRepoDto, Map<String, Object>> {
  @Override public Map<String, Object> transform(@NonNull GitHubAuthRepoDto from) {
    Map<String, Object> values = new HashMap<>();
    values.put(DatabaseConstants.KEY_REPO_DATE, from.getDate());
    values.put(DatabaseConstants.KEY_REPO_ID, from.getId());
    values.put(DatabaseConstants.KEY_REPO_NAME, from.getName());
    values.put(DatabaseConstants.KEY_REPO_IS_PRIVATE,
        from.isPrivate() ? DatabaseConstants.BOOLEAN.TRUE : DatabaseConstants.BOOLEAN.FALSE);
    values.put(DatabaseConstants.KEY_REPO_HTML_URL, from.getHtml_url());
    values.put(DatabaseConstants.KEY_REPO_IS_FORK,
        from.isFork() ? DatabaseConstants.BOOLEAN.TRUE : DatabaseConstants.BOOLEAN.FALSE);
    values.put(DatabaseConstants.KEY_REPO_HOMEPAGE, from.getHomepage());
    values.put(DatabaseConstants.KEY_REPO_STARGAZERS_COUNT, from.getStargazers_count());
    values.put(DatabaseConstants.KEY_REPO_WATCHERS_COUNT, from.getWatchers_count());
    values.put(DatabaseConstants.KEY_REPO_FORKS_COUNT, from.getForks_count());
    values.put(DatabaseConstants.KEY_REPO_LANGUAGE, from.getLanguage());
    return values;
  }
}
