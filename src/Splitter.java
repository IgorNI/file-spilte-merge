

import java.io.*;

public class Splitter {
    private String sourceFile;
    private int blockNum;
    private String dstPath;
    private String fileName;

    public Splitter(String sourceFile, int blockNum, String dstPath) {
        this.sourceFile = sourceFile;
        this.blockNum = blockNum;
        this.dstPath = dstPath;
        this.fileName = this.sourceFile.substring(this.sourceFile.lastIndexOf("/")+ 1);
    }

    public void StartSplit() {
        File file = new File(sourceFile);
        if (file.exists()) {
            int fileSize = (int) file.length();
            int pieceSize = (int) (fileSize / blockNum);
            if ((fileSize - pieceSize * blockNum) != 0) {
                pieceSize = (int) (fileSize / (blockNum - 1));
            }
            byte[] data = new byte[pieceSize];
            FileInputStream fileInputStream = null;
            File dstFold=new File(dstPath);
            if(!dstFold.exists()){
                dstFold.mkdirs();
            }
            try {
                fileInputStream = new FileInputStream(file);
                int realSize = 0;
                int offset = 0;
                int blockIndex = 0;
                while ((realSize = fileInputStream.read(data, 0, pieceSize)) != -1) {

                    FileBlockInfo fileBlockInfo = new FileBlockInfo();
                    fileBlockInfo.setFileName(fileName);
                    fileBlockInfo.setFileNameLength(fileName.getBytes().length);
                    fileBlockInfo.setOffset(offset);
                    fileBlockInfo.setBlockIndex(blockIndex);
                    fileBlockInfo.setAllBlockCount(blockNum);
                    fileBlockInfo.setBlockSize(realSize);
                    fileBlockInfo.setAllSize(fileSize);
                    fileBlockInfo.setData(data);
                    data = new byte[pieceSize];
                    blockIndex++;
                    offset += realSize;
                    new WriteAsync(dstPath + blockIndex, fileBlockInfo.getIntegrateData()).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    class WriteAsync extends Thread {
        private String fileName;
        private byte[] data;

        public WriteAsync(String fileName, byte[] data) {
            this.fileName = fileName;
            this.data = data;
        }

        @Override
        public void run() {
            File file = new File(fileName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(data);
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("第" + fileName + "份写入完成");
        }
    }
}
