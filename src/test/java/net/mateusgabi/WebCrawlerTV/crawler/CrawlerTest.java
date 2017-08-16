package net.mateusgabi.WebCrawlerTV.crawler;

import net.mateusgabi.webcrawler.craw.Crawler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mateus Gabi Moreira <mateusgabimoreira@gmail.com>
 *         on 12/08/2017.
 */
class CrawlerTest {

    Crawler crawler;

    @BeforeEach
    void setUp() {
        this.crawler = Crawler.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldCreateAInstance() {

        assertNotNull(crawler);

    }

    @Test
    void shouldStatusCodeEqualTo200() {
    }

}