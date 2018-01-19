package com.taitascioredev.android.xingtest.data.net

import com.google.gson.Gson
import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.fromJson
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by rrtatasciore on 19/01/18.
 */
class GithubServiceTest {

    companion object {
        val JSON = "[{\n" +
                "\t\"id\": 2399148,\n" +
                "\t\"name\": \"absurdity\",\n" +
                "\t\"full_name\": \"xing/absurdity\",\n" +
                "\t\"owner\": {\n" +
                "\t\t\"login\": \"xing\",\n" +
                "\t\t\"id\": 27901,\n" +
                "\t\t\"avatar_url\": \"https://avatars2.githubusercontent.com/u/27901?v=4\",\n" +
                "\t\t\"gravatar_id\": \"\",\n" +
                "\t\t\"url\": \"https://api.github.com/users/xing\",\n" +
                "\t\t\"html_url\": \"https://github.com/xing\",\n" +
                "\t\t\"followers_url\": \"https://api.github.com/users/xing/followers\",\n" +
                "\t\t\"following_url\": \"https://api.github.com/users/xing/following{/other_user}\",\n" +
                "\t\t\"gists_url\": \"https://api.github.com/users/xing/gists{/gist_id}\",\n" +
                "\t\t\"starred_url\": \"https://api.github.com/users/xing/starred{/owner}{/repo}\",\n" +
                "\t\t\"subscriptions_url\": \"https://api.github.com/users/xing/subscriptions\",\n" +
                "\t\t\"organizations_url\": \"https://api.github.com/users/xing/orgs\",\n" +
                "\t\t\"repos_url\": \"https://api.github.com/users/xing/repos\",\n" +
                "\t\t\"events_url\": \"https://api.github.com/users/xing/events{/privacy}\",\n" +
                "\t\t\"received_events_url\": \"https://api.github.com/users/xing/received_events\",\n" +
                "\t\t\"type\": \"Organization\",\n" +
                "\t\t\"site_admin\": false\n" +
                "\t},\n" +
                "\t\"private\": false,\n" +
                "\t\"html_url\": \"https://github.com/xing/absurdity\",\n" +
                "\t\"description\": \"Absurdly simple AB testing framework\",\n" +
                "\t\"fork\": false,\n" +
                "\t\"url\": \"https://api.github.com/repos/xing/absurdity\",\n" +
                "\t\"forks_url\": \"https://api.github.com/repos/xing/absurdity/forks\",\n" +
                "\t\"keys_url\": \"https://api.github.com/repos/xing/absurdity/keys{/key_id}\",\n" +
                "\t\"collaborators_url\": \"https://api.github.com/repos/xing/absurdity/collaborators{/collaborator}\",\n" +
                "\t\"teams_url\": \"https://api.github.com/repos/xing/absurdity/teams\",\n" +
                "\t\"hooks_url\": \"https://api.github.com/repos/xing/absurdity/hooks\",\n" +
                "\t\"issue_events_url\": \"https://api.github.com/repos/xing/absurdity/issues/events{/number}\",\n" +
                "\t\"events_url\": \"https://api.github.com/repos/xing/absurdity/events\",\n" +
                "\t\"assignees_url\": \"https://api.github.com/repos/xing/absurdity/assignees{/user}\",\n" +
                "\t\"branches_url\": \"https://api.github.com/repos/xing/absurdity/branches{/branch}\",\n" +
                "\t\"tags_url\": \"https://api.github.com/repos/xing/absurdity/tags\",\n" +
                "\t\"blobs_url\": \"https://api.github.com/repos/xing/absurdity/git/blobs{/sha}\",\n" +
                "\t\"git_tags_url\": \"https://api.github.com/repos/xing/absurdity/git/tags{/sha}\",\n" +
                "\t\"git_refs_url\": \"https://api.github.com/repos/xing/absurdity/git/refs{/sha}\",\n" +
                "\t\"trees_url\": \"https://api.github.com/repos/xing/absurdity/git/trees{/sha}\",\n" +
                "\t\"statuses_url\": \"https://api.github.com/repos/xing/absurdity/statuses/{sha}\",\n" +
                "\t\"languages_url\": \"https://api.github.com/repos/xing/absurdity/languages\",\n" +
                "\t\"stargazers_url\": \"https://api.github.com/repos/xing/absurdity/stargazers\",\n" +
                "\t\"contributors_url\": \"https://api.github.com/repos/xing/absurdity/contributors\",\n" +
                "\t\"subscribers_url\": \"https://api.github.com/repos/xing/absurdity/subscribers\",\n" +
                "\t\"subscription_url\": \"https://api.github.com/repos/xing/absurdity/subscription\",\n" +
                "\t\"commits_url\": \"https://api.github.com/repos/xing/absurdity/commits{/sha}\",\n" +
                "\t\"git_commits_url\": \"https://api.github.com/repos/xing/absurdity/git/commits{/sha}\",\n" +
                "\t\"comments_url\": \"https://api.github.com/repos/xing/absurdity/comments{/number}\",\n" +
                "\t\"issue_comment_url\": \"https://api.github.com/repos/xing/absurdity/issues/comments{/number}\",\n" +
                "\t\"contents_url\": \"https://api.github.com/repos/xing/absurdity/contents/{+path}\",\n" +
                "\t\"compare_url\": \"https://api.github.com/repos/xing/absurdity/compare/{base}...{head}\",\n" +
                "\t\"merges_url\": \"https://api.github.com/repos/xing/absurdity/merges\",\n" +
                "\t\"archive_url\": \"https://api.github.com/repos/xing/absurdity/{archive_format}{/ref}\",\n" +
                "\t\"downloads_url\": \"https://api.github.com/repos/xing/absurdity/downloads\",\n" +
                "\t\"issues_url\": \"https://api.github.com/repos/xing/absurdity/issues{/number}\",\n" +
                "\t\"pulls_url\": \"https://api.github.com/repos/xing/absurdity/pulls{/number}\",\n" +
                "\t\"milestones_url\": \"https://api.github.com/repos/xing/absurdity/milestones{/number}\",\n" +
                "\t\"notifications_url\": \"https://api.github.com/repos/xing/absurdity/notifications{?since,all,participating}\",\n" +
                "\t\"labels_url\": \"https://api.github.com/repos/xing/absurdity/labels{/name}\",\n" +
                "\t\"releases_url\": \"https://api.github.com/repos/xing/absurdity/releases{/id}\",\n" +
                "\t\"deployments_url\": \"https://api.github.com/repos/xing/absurdity/deployments\",\n" +
                "\t\"created_at\": \"2011-09-16T12:34:03Z\",\n" +
                "\t\"updated_at\": \"2017-10-27T23:25:23Z\",\n" +
                "\t\"pushed_at\": \"2012-06-09T17:25:45Z\",\n" +
                "\t\"git_url\": \"git://github.com/xing/absurdity.git\",\n" +
                "\t\"ssh_url\": \"git@github.com:xing/absurdity.git\",\n" +
                "\t\"clone_url\": \"https://github.com/xing/absurdity.git\",\n" +
                "\t\"svn_url\": \"https://github.com/xing/absurdity\",\n" +
                "\t\"homepage\": \"\",\n" +
                "\t\"size\": 154,\n" +
                "\t\"stargazers_count\": 61,\n" +
                "\t\"watchers_count\": 61,\n" +
                "\t\"language\": \"Ruby\",\n" +
                "\t\"has_issues\": true,\n" +
                "\t\"has_projects\": true,\n" +
                "\t\"has_downloads\": true,\n" +
                "\t\"has_wiki\": true,\n" +
                "\t\"has_pages\": false,\n" +
                "\t\"forks_count\": 7,\n" +
                "\t\"mirror_url\": null,\n" +
                "\t\"archived\": false,\n" +
                "\t\"open_issues_count\": 1,\n" +
                "\t\"license\": null,\n" +
                "\t\"forks\": 7,\n" +
                "\t\"open_issues\": 1,\n" +
                "\t\"watchers\": 61,\n" +
                "\t\"default_branch\": \"master\"\n" +
                "}]"
    }

    @Mock lateinit var api: GithubApi

    lateinit var service: GithubService

    @Before
    fun setUp() {
        initMocks(this)
        service = GithubService(api)
    }

    @Test
    fun getXingRepos_should_return_expected_value() {
        // Given
        val expectedValue = Gson().fromJson<List<RepositoryEntity>>(JSON)
        val expectedObservable = Observable.just(expectedValue)
        `when`(api.getXingRepos(anyInt(), anyInt())).thenReturn(expectedObservable)

        // When
        val resultObservable = service.getXingRepos(1)

        // Then
        `verify`(api).getXingRepos(1, 10)
        assertEquals(expectedObservable, resultObservable)
        resultObservable.test().assertValue(expectedValue)
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }
}