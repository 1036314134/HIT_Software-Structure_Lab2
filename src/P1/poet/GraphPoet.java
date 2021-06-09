/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    // GraphPoet使用一个用于派生单词关联图的文本语料库初始化
    // 图中的顶点是单词
 
    // Representation invariant:
    // 图不应该为空
    // 所有的边都应为正
    
    // Safety from rep exposure:
    //  所有fields都设为了private
    
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
    	BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(corpus));

			String line = new String();
			String lastWord = null;
			List<String> words = new ArrayList<>();

			while ((line = reader.readLine()) != null) {
				words = Arrays.asList(line.split(" "));
				int length = words.size();

				if (lastWord != null) {
					String source = lastWord.toLowerCase();
					String target = words.get(0).toLowerCase();
					graph.add(source);
					graph.add(target);

					if (graph.targets(source).containsKey(target)) {
						graph.set(source, target, graph.targets(source).get(target) + 1);
					}else {
						graph.set(source, target, 1);
					}	
				}

				for (int i = 1; i < length; i++) {
					String source = words.get(i - 1).toLowerCase();
					String target = words.get(i).toLowerCase();
					graph.add(source);
					graph.add(target);

					if (graph.targets(source).containsKey(target)) {
						graph.set(source, target, graph.targets(source).get(target) + 1);
					}else {
						graph.set(source, target, 1);
					}
				}
				
				lastWord = words.get(length-1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		reader.close();
		checkRep();
    }
    
    public void checkRep() {
    	Set<String> vertices = graph.vertices();
    	assert !vertices.isEmpty();
    	
		for (String v : vertices) {
			for (Integer x : graph.targets(v).values()) {
				assert x > 0;
			}
		}
    }
    
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
    	List<String> words = Arrays.asList(input.split(" "));
    	int length = words.size();
		String poem = new String();

		for (int i = 0; i < length - 1; i++) {
			String thisWord = words.get(i).toLowerCase();
			String nextWord = words.get(i+1).toLowerCase();

			String maxWord = null;
			int maxWeight = -1;

			poem = poem + " " + thisWord;

			for (String j : graph.targets(thisWord).keySet()) {
				String targetWord = j.toLowerCase();
				int Weight = graph.targets(thisWord).get(targetWord);

				for (String k : graph.targets(targetWord).keySet()) {
					String endWord = k.toLowerCase();
					Weight += graph.targets(targetWord).get(endWord);
					
					if (nextWord.equals(endWord) && Weight > maxWeight) {
						maxWeight = Weight;
						maxWord = targetWord;
						break;
					}
				}
			}

			if (maxWord != null) {
				poem = poem + " " + maxWord;
			}
		}
		
		poem = poem + " " + words.get(length-1).toLowerCase();
		poem = poem.trim();
		poem = poem.substring(0, 1).toUpperCase() + poem.substring(1);
		
		checkRep();
		return poem;
    }
    
    
    /**
	 * 返回由单词构成的图
	 * 
	 * @return 由单词构成的图
	 */
	public String toString() {
		return this.graph.toString();
	}
}
   
