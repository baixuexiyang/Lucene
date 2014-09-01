###OPTIONS
    此项目是本人的毕业设计，内容是基于Lucene技术的本地PDF文件搜索，实现了根据内容、摘要、作者等搜索，web框架使用了structs技术

###环境：
    ->JDK：
       Version：1.6.0_10-rc2


    ->eclipse：
    Version: 1.0.0.v201104180000-797B1CcNBHGC_DRAwXT3B


    ->Tomcat：
    Version：v7.0


###结构：
    ->F:/pdfDir    pdf文件存放目录
    ->F:/txtDir    转换txt文件存放目录
    ->F:/indexDir  创建索引存放目录
    ###参数是可以配置的

###访问：http://localhost:端口/search.html

###最新版本改进：
    
    1.能够对多级目录进行搜索
    2.文件路劲、索引路劲放在配置文件lucene.properties中
        file.path ==> 对应搜索目录
        index.path ==>索引存放文件
    3.异常处理，将异常由输出到控制台转到错误页面
    4.搜索关键字乱码处理，使用URLDecoder.decode函数进行转码
    6.当没有文件改变时，索引只被创建一次
    7.新增文件监听事件，当有文件删除、新增、修改时，索引将被重新创建
        （监听事件间隔放在配置文件中）
    

###注意：
    1.直接访问search路劲，搜索不到内容，此时索引未创建，需要访问根目录创建索引
    2.项目编码是UTF-8
      3.pdf文件必须是标准的pdf文件，否则会报错，最好使用此文件夹下的pdfDir中的文件