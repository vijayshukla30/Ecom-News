package ecom.news

import com.rss.Feed
import com.rss.FetchUrl
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_SUBADMIN'])
class FeedController {

    def feedService

    def readRssFeed() {
        FetchUrl url = new FetchUrl(urlLink: "http://timesofindia.indiatimes.com/rssfeeds/1081479906.cms")
        url.save(flush: true, failOnError: true)
        feedService.readRssFeedXml(url)
        List feedList = Feed.createCriteria().listDistinct {
            eq("url", url)
        }
        render view: "showFeed", model: [feedList: feedList]
    }
}