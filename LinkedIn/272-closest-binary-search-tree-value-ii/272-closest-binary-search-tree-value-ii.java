class Solution {
    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> nums = new ArrayList();
        inorder(root, nums);
        
        Collections.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
            }
        });
        return nums.subList(0, k);
    }
}