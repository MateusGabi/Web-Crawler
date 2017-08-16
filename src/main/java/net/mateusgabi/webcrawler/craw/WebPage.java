/*
 * The MIT License
 *
 * Copyright 2017 mateu.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.mateusgabi.webcrawler.craw;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Mateus Gabi Moreira
 * @email mateusgabimoreira@gmail.com
 */
public class WebPage {

    public WebPage() {
        this.id = next_id;
        next_id++;
    }

    public WebPage setURL(String url) {

        this.url = new WebPage.URL();

        this.url.set(url);

        return this;
    }

    public URL getURL() {
        return this.url;
    }

    public URL getUrl() {
        return url;
    }

    public WebPage setUrl(URL url) {
        this.url = url;

        return this;
    }

    public String getTitle() {
        return title;
    }

    public WebPage setTitle(String title) {
        this.title = title;

        return this;
    }

    public String getAuthor() {
        return author;
    }

    public WebPage setAuthor(String author) {
        this.author = author;

        return this;
    }

    public long getCraw_visits() {
        return craw_visits;
    }

    public WebPage setCraw_visits(long craw_visits) {
        this.craw_visits = craw_visits;

        return this;
    }

    public Date getIndexed_at() {
        return indexed_at;
    }

    public WebPage setIndexed_at(Date indexed_at) {
        this.indexed_at = indexed_at;

        return this;
    }

    public Date getLast_visit_at() {
        return last_visit_at;
    }

    public WebPage setLast_visit_at(Date last_visit_at) {
        this.last_visit_at = last_visit_at;

        return this;
    }

    public Collection<String> getKeywords() {
        return keywords;
    }

    public WebPage setKeywords(Collection<String> keywords) {
        this.keywords = keywords;

        return this;
    }

    public Status getStatus() {
        return status;
    }

    public WebPage setStatus(Status status) {
        this.status = status;

        return this;
    }

    public long getId() {
        return id;
    }

    public WebPage setId(long id) {
        this.id = id;

        return this;
    }

    public Collection<URL> getLinks() {
        return links;
    }

    public WebPage setLinks(Collection<URL> links) {
        this.links = links;
        return this;
    }

    static class URL {

        private String url;

        void set(String url) {

            this.url = url;

        }

        @Override
        public String toString() {
            return url;
        }
    }

    public static enum Status {

        OK, ERROR;

    }

    private WebPage.URL url;
    private String title;
    private String author;
    private long craw_visits;
    private Date indexed_at;
    private Date last_visit_at;
    private Collection<String> keywords;

    private Collection<WebPage.URL> links;

    private WebPage.Status status;

    private long id;

    private static long next_id = 1L;

} // WebPage
