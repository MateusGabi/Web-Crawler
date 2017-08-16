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

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import net.mateusgabi.webcrawler.db.DAO;
import net.mateusgabi.webcrawler.db.WebPageDAO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author Mateus Gabi Moreira
 * @email mateusgabimoreira@gmail.com
 */
public class Crawler {

    public static Crawler getInstance() {
        return ourInstance;
    }

    private Crawler() {
        this.client = new OkHttpClient();
        this.toVisit = new ArrayList<WebPage.URL>();
        this.visiteds = new ArrayList<WebPage.URL>();
    }

    private WebPage requestWebPage(WebPage.URL webPageURL) throws IOException {

        Request request = new Request.Builder()
                .url(webPageURL.toString())
                .build();

        Response response = this.client.newCall(request).execute();

        String body = response.body().string();

        return new WebPage()
                .setURL(webPageURL.toString())
                .setAuthor(this.getAuthorPage(response))
                .setKeywords(this.getKeywordPage(response))
                .setLast_visit_at(new Date())
                .setStatus(this.getStatusPage(response))
                .setLinks(this.getLinksInPage(body))
                .setTitle(this.getTitlePage(body));
    }

    private Collection<WebPage.URL> getLinksInPage(String body) {

        Matcher matcher = linkPattern.matcher(body);

        Collection<WebPage.URL> links = new ArrayList<>();

        while (matcher.find()) {

            LOGGER.log(Level.INFO, "A link was found: " + matcher.group());

            // Adicionar na coleção
            links.add(new WebPage().setURL(matcher.group().toString()).getURL());
        }

        return links;
    }

    private String getTitlePage(String body) {

        Matcher matcher = titlePattern.matcher(body);

        String title = "no title";

        if (matcher.find()) {
            title = matcher.group().toString().substring(7);

            LOGGER.log(Level.INFO, "Title was found: " + title);
        }

        return title;
    }

    private WebPage.Status getStatusPage(Response response) {

        return response.code() == 200 ? WebPage.Status.OK : WebPage.Status.ERROR;
    }

    private Collection<String> getKeywordPage(Response response) {
        // TODO
        return null;
    }

    private String getAuthorPage(Response response) {
        // TODO

        return null;
    }

    public Crawler seedURL(String url) {

        LOGGER.log(Level.INFO, url + " was seeded.");

        this.toVisit.add(new WebPage().setURL(url).getURL());

        return this;
    }

    public void run() {

        WebPage.URL url;

        while (this.toVisit.iterator().hasNext()) {

            url = this.toVisit.iterator().next();

            this.toVisit.remove(url);

            LOGGER.log(Level.INFO, "Trying to connect to: " + url);

            try {

                WebPage webPage = this.requestWebPage(url);

                LOGGER.log(Level.INFO, "Response status is " + webPage.getStatus());

                // insere no Banco

                DAO.Response insertionResponse = new WebPageDAO().adicionar(webPage);

                LOGGER.log(insertionResponse.getStatus().toString().equals("OK") ? Level.INFO : Level.SEVERE, insertionResponse.getMessage());

                // Busca novas páginas

                webPage
                        .getLinks()
                        .stream()
                        .forEach((WebPage.URL u) -> {

                            String[] urlSplited = u.toString().split("[.]");
                            String urlType = urlSplited[urlSplited.length > 1 ? urlSplited.length - 1 : 0];

                            if (Arrays
                                    .stream(Crawler.this.notInterestingFiles)
                                    .anyMatch((String urlNotInteresting) -> urlType.equals(urlNotInteresting)))
                            {

                                LOGGER.log(Level.INFO, "URL " + u + " is not interesting");

                            }
                            else {

                                LOGGER.log(Level.INFO, "URL " + u + " is interesting");

                                this.seedURL(u.toString());

                            }
                        });

            } catch (IOException e) {
                LOGGER.log(Level.INFO, "IOException :c");
            }

        }

    }

    private OkHttpClient client;
    private Collection<WebPage.URL> toVisit;
    private Collection<WebPage.URL> visiteds;

    private final static String[] notInterestingFiles = new String[]{
        "css", "ico", "js", "jpg", "png", "gif", "txt"
    };

    private final static String regex = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    private final static String regex_Title = "<title>([^<]+)";
    private final static Pattern linkPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private final static Pattern titlePattern = Pattern.compile(regex_Title, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    private static Crawler ourInstance = new Crawler();
    private final static Logger LOGGER = Logger.getLogger(Crawler.class.getName());

} // Crawler
