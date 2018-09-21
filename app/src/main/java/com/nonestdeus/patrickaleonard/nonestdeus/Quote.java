package com.nonestdeus.patrickaleonard.nonestdeus;

/**
 * Created by Patrick Leonard on 7/22/2015.
 */
public class Quote {
    public int quoteTextId;
    public int quoteAuthorId;

    public Quote(int textId, int authorId) {
        this.quoteTextId = textId; this.quoteAuthorId = authorId;
    }
}
