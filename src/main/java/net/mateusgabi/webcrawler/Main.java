/*
 * The MIT License
 *
 * Copyright 2017 Mateus Gabi Moreira.
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
package net.mateusgabi.webcrawler;

import net.mateusgabi.webcrawler.craw.Crawler;

import java.util.Arrays;

/**
 *
 * @author Mateus Gabi Moreira
 * @email mateusgabimoreira@gmail.com
 */
public class Main {

    public static void main(String[] args) {

        /*
        *
        * to execute:
        * java -cp WebCrawlerTV.jar net.mateusgabi.webcrawler.Main [site-uri]
        */


        if (args.length == 0) {

            System.out.println("Use     :   java -cp WebCrawlerTV.jar net.mateusgabi.webcrawler.Main [site-uri] ...");

        }

        Arrays.stream(args).forEach(link -> {

            Crawler
                    .getInstance()
                    .seedURL(link)
                    .run();

        });
    }

}
