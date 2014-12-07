TextSummary
===========
A Java implementation of TextRank; an unsupervised method to summarize news articles.  Based on work done by Rada Mihalcea and Paul Tarau.  Methodology taken from: http://web.eecs.umich.edu/~mihalcea/papers/mihalcea.emnlp04.pdf

Uses OpenNLP library to seperate sentences and Lucene library for stop word removal and tokenization.

TextSummary's results will be compared to various other text summarizers:
* http://freesummarizer.com/
* http://autosummarizer.com/
* http://textcompactor.com/

News Articles used:
* http://www.reuters.com/article/2014/12/04/us-vatican-economy-pell-idUSKCN0JI1CG20141204?feedType=RSS&feedName=worldNews
* http://thinkprogress.org/climate/2014/12/04/3599528/cruise-lines-sewage-2014/
* http://articles.economictimes.indiatimes.com/2014-12-02/news/56648982_1_syria-british-man-jihadis
* http://www.thelocal.de/20141204/merkel-speaks-out-against-net-neutrality
* http://www.bbc.com/news/world-europe-30322198

Java File List
============
* SummaryBot.java: Runs the summary process.
* PageRanker.java: Runs page rank algorithm.
* MiscUtils.java: Creates weighted adjacency matrix between sentences.
* LuceneSentenceProcessor: Uses Lucene to remove stop words and tokenize.
* OpenNLPSentenceProcessor: Removes sentences and uses POS tagging.
* testMain.java: An example that summarizes the news article stored in a text file.

References
==========
1. http://pages.cs.wisc.edu/~goldberg/publications/summarization.pdf
2. http://web.eecs.umich.edu/~mihalcea/papers/mihalcea.emnlp04.pdf
3. http://www.extractor.com/IR2000.pdf
4. http://www.kiv.zcu.cz/~jstein/publikace/isim2004.pdf
5. http://www.cs.cmu.edu/afs/cs/project/jair/pub/volume22/erkan04a-html/erkan04a.html
6. http://ilpubs.stanford.edu:8090/422/1/1999-66.pdf
