package com.nonestdeus.patrickaleonard.nonestdeus.quotes;
/**
 * Created by Patrick Leonard on 7/22/2015.
 */
public class Quote {
    public static final String ARG_QUOTE_NUM = "quote-num";
    public static final String ARG_QUOTE_AUTHOR = "quote-author";
    public static final String ARG_QUOTE_TEXT = "quote-text";
    public final int quoteTextId;
    public final int quoteAuthorId;
    public int quoteNum;
    public Quote(int textId, int authorId) {
        this.quoteTextId = textId; this.quoteAuthorId = authorId;
    }
}
