

import java.util.Scanner;

public class SpliterMain {
    public static void main(String[] args){
        Scanner cin=new Scanner(System.in);
        String sourceFile = null;
        int blockNum = 0;
        String dstFolder = null;
        System.out.println("请输入文件全路径名...");
        while (cin.hasNext()){
            sourceFile=cin.nextLine();
            System.out.println("请输入需要分割的分数...");
            blockNum=cin.nextInt();
            cin.nextLine();
            System.out.println("请输入目标路径...");
            dstFolder=cin.nextLine();
            Splitter splitter=new Splitter(sourceFile,blockNum,dstFolder);
            splitter.StartSplit();
            System.out.println("请输入文件全路径名...");
        }
    }
}
