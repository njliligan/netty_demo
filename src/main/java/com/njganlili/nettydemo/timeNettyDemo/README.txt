虽然4个字节很小，但是也并不一定能被接受完全。
TimeDecoder.class就是用来处理不能接受完全的问题的。
这里也有三种方法
1，创建内部的缓冲区，直到读到四个字节或者以上才输出。

2，是实现ByteToMessageDecoder 加入pipol