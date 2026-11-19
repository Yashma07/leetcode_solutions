class Solution {
    public int minimizeSum(int[] nums) {
        int sm1=Integer.MAX_VALUE;
        int sm2=Integer.MAX_VALUE;
        int sm3=Integer.MAX_VALUE;
        int max1=Integer.MIN_VALUE;
        int max2=Integer.MIN_VALUE;
        int max3=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>max3){
                if(nums[i]>max2){
                    if(nums[i]>max1){
                        max3=max2;
                        max2=max1;
                        max1=nums[i];
                    }
                    else{
                        max3=max2;
                        max2=nums[i];
                    }
                }
                else{
                    max3=nums[i];
                }
            }
            if(nums[i]<sm3){
                if(nums[i]<sm2){
                    if(nums[i]<sm1){
                        sm3=sm2;
                        sm2=sm1;
                        sm1=nums[i];
                    }
                    else{
                        sm3=sm2;
                        sm2=nums[i];
                    }
                }
                else{
                    sm3=nums[i];
                }
            }
        }
        return Math.min(Math.min(max1-sm3,max3-sm1),max2-sm2);
    }
}