

import java.util.Scanner;
public class MegerMain {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String sourceFile = null;
        int blockNum = 0;
        String dstFolder = null;
        System.out.println("请输入文件全路径名...");
        while (cin.hasNext()) {
            sourceFile = cin.nextLine();

            System.out.println("请输入目标路径...");
            dstFolder = cin.nextLine();
            Merger merger = new Merger(sourceFile, dstFolder);
            merger.StartMerge();
            System.out.println("请输入文件全路径名...");
        }
    }
}
