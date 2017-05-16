

public class FileBlockInfo {
    private String fileName;//文件名
    private int fileNameLength;//文件名长度
    private int offset;
    private int blockIndex;//第几块
    private int allBlockCount;//文件总分数
    private int blockSize;//该文件块长度
    private int allSize;//文件块总长度
    private byte[] data;//该块数据

    @Override
    public String toString() {
        return "FileBlockInfo{" +
                "fileNameLength=" + fileNameLength +
                ", offset=" + offset +
                ", blockIndex=" + blockIndex +
                ", allBlockCount=" + allBlockCount +
                ", blockSize=" + blockSize +
                ", allSize=" + allSize +
                '}';
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public FileBlockInfo() {
    }

    public FileBlockInfo(byte[] header) {
        fileNameLength = ByteUtils.getIntFromBytes(header, 0);
        offset = ByteUtils.getIntFromBytes(header, 4);
        blockIndex = ByteUtils.getIntFromBytes(header, 8);
        allBlockCount = ByteUtils.getIntFromBytes(header, 12);
        blockSize = ByteUtils.getIntFromBytes(header, 16);
        allSize = ByteUtils.getIntFromBytes(header, 20);
        System.out.println(toString());
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileNameLength() {
        return fileNameLength;
    }

    public void setFileNameLength(int fileNameLength) {
        this.fileNameLength = fileNameLength;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getBlockIndex() {
        return blockIndex;
    }

    public void setBlockIndex(int blockIndex) {
        this.blockIndex = blockIndex;
    }

    public int getAllBlockCount() {
        return allBlockCount;
    }

    public void setAllBlockCount(int allBlockCount) {
        this.allBlockCount = allBlockCount;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getAllSize() {
        return allSize;
    }

    public void setAllSize(int allSize) {
        this.allSize = allSize;
    }

    public byte[] getIntegrateData() {
        byte[] integeratedData = new byte[24 + fileNameLength + blockSize];
        ByteUtils.setIntBytes(integeratedData, 0, fileNameLength);
        ByteUtils.setIntBytes(integeratedData, 4, offset);
        ByteUtils.setIntBytes(integeratedData, 8, blockIndex);
        ByteUtils.setIntBytes(integeratedData, 12, allBlockCount);
        ByteUtils.setIntBytes(integeratedData, 16, blockSize);
        ByteUtils.setIntBytes(integeratedData, 20, allSize);
        System.arraycopy(fileName.getBytes(), 0, integeratedData, 24, fileNameLength);
        System.arraycopy(data, 0, integeratedData, 24 + fileNameLength, blockSize);
        return integeratedData;
    }
}
