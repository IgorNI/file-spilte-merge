

import java.io.*;

public class Merger {
    private String sourcePath;
    private String destinyPath;
    private String fileName;

    public Merger(String sourcePath, String desPath) {
        this.sourcePath = sourcePath;
        this.destinyPath = desPath;
    }

    public void StartMerge() {
        File file = new File(sourcePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            FileBlockInfo fileBlockInfo;
            for (int i = 0; i < files.length; i++) {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(files[i]);
                    byte[] fileInfoData = new byte[24];
                    fileInputStream.read(fileInfoData, 0, 24);
                    fileBlockInfo = new FileBlockInfo(fileInfoData);
                    byte[] fileNameData = new byte[fileBlockInfo.getFileNameLength()];
                    fileInputStream.read(fileNameData, 0, fileNameData.length);
                    if (i == 0){
                        fileName = destinyPath + new String(fileNameData);
                        System.out.println("文件名--》"+fileName);
                        RandomAccessFile randomAccessFile=new RandomAccessFile(fileName,"rw");
                        randomAccessFile.setLength(fileBlockInfo.getAllSize());
                        randomAccessFile.close();
                    }

                    byte[] realData = new byte[fileBlockInfo.getBlockSize()];
                    fileInputStream.read(realData, 0, realData.length);
                    new MultiMergeThread(fileName, fileBlockInfo.getOffset(), realData).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("合并完成");
        }
    }

    class MultiMergeThread extends Thread {
        private long offset;
        private byte[] data;
        private String fileName;

        public MultiMergeThread(String fileName, long offset, byte[] data) {
            this.offset = offset;
            this.data = data;
            this.fileName = fileName;
        }

        @Override
        public void run() {
            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(fileName, "rw");
                randomAccessFile.seek(offset);
                randomAccessFile.write(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
