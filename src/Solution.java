import java.util.*;

class Solution {

    /**
      Definition for a binary tree node.
     */

      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(0,0,preorder.length-1,preorder,inorder);
    }
    public TreeNode buildTree(int prestart, int inStart, int inEnd,int preorder[], int inorder[]){

        if(inStart>inEnd || prestart>inorder.length){
            return null;
        }

        TreeNode root = new TreeNode(preorder[prestart]);
        int index=0;
        for(int i=inStart;i<=inEnd;i++){
            if(inorder[i]==preorder[prestart]){
                index = i;
                break;
            }
        }

        root.left = buildTree(prestart+1,inStart,index-1,preorder,inorder);
        root.right = buildTree(prestart+(index-inStart)+1,index+1,inEnd,preorder,inorder);

        return root;
    }

    public static void main(String[] args) {
        Solution s =  new Solution();

        //int b[] = {0,1,0,3,12};
        String word = "My name is mohit bhagwani";

        String arr[] ={"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
                //[[2,1,1],[1,1,0],[0,1,1]]

        //s.floodFill(arr,1,1,2);

        String s1 = "abcabcbb";
        String s2 = "dcda";

        //s.orangesRotting(arr);
        // String str = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        //  "1010"
        // "1011"

        int intervals[][] = {{1,3},{6,9}};
        int preorder[] = {3,9,8,10,20,15,7};
        int inorder[] = {8,9,10,3,15,20,7};
        int arr1[] = {1,5,11,5};
        int amount = 3;

        System.out.println(s.buildTree(preorder,inorder));
    }
}

