Unsupervised Article Summarization
========================================================
author: Alex Shum and Robert Corell-Johanns

Goal
========================================================
- News articles are too long.
- Modern attention span makes reading news difficult.
- The gist of a news article can be summarized in 3 - 5 sentences.
- Rank sentences based on how well it summarizes the entire article.
- Extract the most relevant sentences in a news article algorithmically.

Article Summarization
=====================
- Abstractive vs Extractive.
- Abstractive techniques generate text to summarize article.
- Extractive techniques only return text that was present in the article.
- Earliest extractive techniques used decision trees.
- Unsupervised methods include latent semantic analysis and graph based methods.
- No labelled dataset so we use use unsupervised techniques.

Graph based methodology
========================================================
- Sentence ranking algorithm based on PageRank.
- Pages are represented as vertices in a graph.
- Edges represent a hyperlink from one page to another.
- PageRank calculates a probability distribution that a person randomly arrives at a particular page.
- PageRank counts the number and quality of links to a webpage.
- A webpage has high PageRank if it is linked to by webpages with high PageRank.

Graph based ranking algorithm
========================================================
- Summary sentences are likely to be similar to other sentences.
- Important sentences are similar to other important sentences.
- Represent sentences as vertices in a graph.
- Edges between sentences are weighted based on a distance metric.
- TextRank distance metric:
$$d(s_i, s_j) = \frac{|\{w_k | w_k \in S_i & w_k \in S_j \}|}{log(|S_i|) + log(|S_j|)}$$
- Sentence similarly based on number of shared words divided by log lengths.

Algorithm and preprocessing
========================================================
1. Parse document into sentences using OpenNLP.
2. Remove stop words with little semantic meaning.
3. Calculate similarity matrix between sentences.
4. Run iterative PageRank algorithm on similarity matrix.
5. Return sentences with the highest pagerank.

Methodology
========================================================
Compare textrank against other automated summarizers:

- http://freesummarizer.com

- http://autosummarizer.com

- http://textcompactor.com

Results
========================================================
First Article:
http://www.aljazeera.com/news/middleeast/2014/11/us-led-air-raids-target-syria-rebel-groups-2014116123052671427.html

Second Article:
http://www.thelocal.de/20141204/merkel-speaks-out-against-net-neutrality

TextRank - First Article
========
- US-led air raids have struck Syrian rebels not linked to the Islamic State of Iraq and the Levant (ISIL), expanding the coalitions raids for the second time to other groups fighting in Syria.

- Activists in Syria told Al Jazeera the raids struck the Ahrar al-Sham rebel group in the northern province of Idlib, and the Syrian branch of al-Qaeda, the Nusra Front, in western Aleppo on Thursday.

- One of Syria's largest armed opposition groups, Ahrar al-Sham is a key member of the Islamic Front coalition, which seeks a state run on Islamic principles as its fights to topple Syrian President Bashar al-Assad.

- Air raids also targeted the Nusra Front in the Reef al-Muhandisin area of western Aleppo, reportedly killing several of the groups' fighters as well as six civilians.

- The US and Arab states have been carrying out air strikes against ISIL in Syria since September 23, in a bid to prevent the armed group from seizing more territory in the conflict-hit nation.


freesummarizer - First Article
==============
- US-led air raids have struck Syrian rebels not linked to the Islamic State of Iraq and the Levant (ISIL), expanding the coalitions raids for the second time to other groups fighting in Syria.

- Activists in Syria told Al Jazeera the raids struck the Ahrar al-Sham rebel group in the northern province of Idlib, and the Syrian branch of al-Qaeda, the Nusra Front, in western Aleppo on Thursday.

- One of Syria's largest armed opposition groups, Ahrar al-Sham is a key member of the Islamic Front coalition, which seeks a state run on Islamic principles as its fights to topple Syrian President Bashar al-Assad.

- There have been no reported attaks on groups outside ISIL since, but the latest air raids come after the group made gains against Western-backed rebel fighters in the Idlib region.

autosummarizer - First Article
==============
- US-led air raids have struck Syrian rebels not linked to the Islamic State of Iraq and the Levant , expanding the coalitions raids for the second time to other groups fighting in Syria.

- Activists in Syria told Al Jazeera the raids struck the Ahrar al-Sham rebel group in the northern province of Idlib, and the Syrian branch of al-Qaeda, the Nusra Front, in western Aleppo on Thursday.

- Air raids also targeted the Nusra Front in the Reef al-Muhandisin area of western Aleppo, reportedly killing several of the groups fighters as well as six civilians.

- There have been no reported attacks on groups outside ISIL since, but the latest air raids come after the group made gains against Western-backed rebel fighters in the Idlib region.

textcompactor.com - First Article
=================
- US-led air raids have struck Syrian rebels not linked to the Islamic State of Iraq and the Levant (ISIL), expanding the coalitions raids for the second time to other groups fighting in Syria.

- Activists in Syria told Al Jazeera the raids struck the Ahrar al-Sham rebel group in the northern province of Idlib, and the Syrian branch of al-Qaeda, the Nusra Front, in western Aleppo on Thursday.

- At least six coalition air raids struck Idlib overnight, with one targeting an Ahrar al-Sham position in the village of Babska.

- One of Syria's largest armed opposition groups, Ahrar al-Sham is a key member of the Islamic Front coalition, which seeks a state run on Islamic principles as its fights to topple Syrian President Bashar al-Assad.

- Air raids also targeted the Nusra Front in the Reef al-Muhandisin area of western Aleppo, reportedly killing several of the groups' fighters as well as six civilians.

TextRank - Second Article
========
- Merkel said that some key services for the digital economy would require reliable transmission quality and should therefore be treated differently than other data.

- Social Democratic Party (SPD) MEP Petra Kammerevert told The Local that this would make it more difficult to find a common European position on net neutrality.

- "If Merkel goes into negotiations with the position she's outlined today, it will be very difficult for the European Council to find a common position," she said.

- The Council, composed of the heads of state and government of all the EU member countries, must find a common negotiating position to deal with the European Parliament, which voted against a European Commission plan for regulations that would allow a two-tier internet in April.

- This is not the net neutrality we want, but a move towards the creation of a two-tier network where content becomes preferred based on who pays for it to make it so," said Markus Beckedahl, Berlin-based founder of netzpolitik.

freesummarizer - Second Article
==============
- Merkel said that some key services for the digital economy would require reliable transmission quality and should therefore be treated differently than other data.

- At the Vodafone-hosted Digitising Europe conference in Berlin, she called for a splitting of services, "one for free internet, and the other for special services", adding that it was up to Brussels to negotiate how it would work.

- Merkel added that these special services would run over existing internet infrastructure.

- Social Democratic Party (SPD) MEP Petra Kammerevert told The Local that this would make it more difficult to find a common European position on net neutrality.

- "If Merkel goes into negotiations with the position she's outlined today, it will be very difficult for the European Council to find a common position," she said.

autosummarizer - Second Article
==============
- At the Vodafone-hosted Digitising Europe conference in Berlin, she called for a splitting of services, one for free internet, and the other for special services , adding that it was up to Brussels to negotiate how it would work.

- Social Democratic Party MEP Petra Kammerevert told The Local that this would make it more difficult to find a common European position on net neutrality.

- If Merkel goes into negotiations with the position shes outlined today, it will be very difficult for the European Council to find a common position, she said.

- The Council, composed of the heads of state and government of all the EU member countries, must find a common negotiating position to deal with the European Parliament, which voted against a European Commission plan for regulations that would allow a two-tier internet in April.

- If you poke holes into net neutrality the way Chancellor Merkel suggests, then its no longer democratic, he said.

textcompactor.com - Second Article
=================
- Merkel said that some key services for the digital economy would require reliable transmission quality and should therefore be treated differently than other data.

- At the Vodafone-hosted Digitising Europe conference in Berlin, she called for a splitting of services, "one for free internet, and the other for special services", adding that it was up to Brussels to negotiate how it would work.

- "An innovation-friendly internet means that there is a guaranteed reliability for special services," she said. "These can only develop when predictable quality standards are available".

- Merkel added that these special services would run over existing internet infrastructure.

- Social Democratic Party (SPD) MEP Petra Kammerevert told The Local that this would make it more difficult to find a common European position on net neutrality.


Summary of results
==================
Article 1:
freesummarizer: 3 out of 4 sentences were similar
autosummarizer: 3 out of 4 sentences were similar
textcompactor: 4 out of 5 sentences were similar

Article 2: 
freesummarizer: 3 out of 5 sentences were similar
autosummarizer: 3 out of 5 sentences were similar
textcompactor: 2 out of 5 sentences were similar

Conclusion
==========
- Other summarizers use other heuristics such as sentence position in the article and paragraph position.
- TextRank has comparable results without heuristics.


Questions?
