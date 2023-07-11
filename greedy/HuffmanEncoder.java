import java.util.*;

//Implementation of Huffman algorithm to build a prefix-free tree for a given text
public class HuffmanEncoder {

        public static void main(String[] args) { encode(args[0]); }

        private static void encode(String str) {
            HuffmanNode tree = buildHuffmanTree(buildNodeMap(str));
            tree.printBfs();
        }

        //Building a map of nodes to calculate characters frequency.
        private static Map<Character, HuffmanNode> buildNodeMap(String str) {

            Map<Character, HuffmanNode> map = new HashMap<>();

            str.chars().forEach( letter -> {
                char c = (char) letter;
                map.putIfAbsent(c, new HuffmanLeaf(c, 0));
                map.computeIfPresent(c, (w, node) -> node.increaseFreq());
            });
            return map;
        }

        //Huffman algorithm (CLRS 4th edition version).
        private static HuffmanNode buildHuffmanTree(Map<Character, HuffmanNode> map) {

            PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>();
            map.values().forEach(v -> queue.offer(v));

            for (int i = 0; i < map.size() - 1; i++) {
                HuffmanNode left = queue.poll();
                HuffmanNode right = queue.poll();
                HuffmanNode node = new HuffmanNode(left, right, left.getFreq() + right.getFreq());
                queue.offer(node);
            }
            return queue.poll();
        }
}
//Superclass representing a tree internal node.
class HuffmanNode implements Comparable<HuffmanNode>{

    private HuffmanNode left;
    private HuffmanNode right;
    protected long freq;

    public HuffmanNode(HuffmanNode left, HuffmanNode right, long freq) {
        this.left = left;
        this.right = right;
        this.freq = freq;
    }
    public HuffmanNode(long freq) {
        this.freq = freq;
    }
    public HuffmanNode getLeft() {
        return left;
    }
    public HuffmanNode getRight() {
        return right;
    }
    public long getFreq() {
        return freq;
    }
    public HuffmanNode increaseFreq(){
        freq++;
        return this;
    }
    @Override
    public String toString() {
        return String.format("Node freq: %d", this.getFreq());
    }
    @Override
    public int compareTo(HuffmanNode o) {
        return o.getFreq() > this.getFreq() ? -1 : 1;
    }

    // A BFS traversal to print all nodes from each level of the tree. Used to check if the tree was built properly.
    public void printBfs() {
        HuffmanNode node = this;
        ArrayDeque<HuffmanNode> queue = new ArrayDeque<>();
        queue.offer(node);

        while(!queue.isEmpty()){
            node = queue.poll();
            System.out.println(node);
            if (node.getLeft() != null) queue.offer(node.getLeft());
            if (node.getRight() != null) queue.offer(node.getRight());
        }
    }
}
//Subclass representing a tree leaf.
class HuffmanLeaf extends HuffmanNode {

    private char letter;

    public HuffmanLeaf(char letter, long freq) {
        super(freq);
        this.letter = letter;
    }
    public char getLetter() {
        return letter;
    }
    @Override
    public String toString() {
        return String.format("Leaf freq: %d, char: %c", this.getFreq(), this.getLetter());
    }
}
