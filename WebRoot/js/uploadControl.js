var fileCount = -1;
$(function(){
    var $list = $("#thelist");
    var  uploader ;// 实例化   
    uploader = WebUploader.create({ 
           auto:false, //是否自动上传
            pick: {
                id: '#attach',
                name:"file",  //这个地方 name没什么用，调试器中input的名字确实改过来了。但是提交到后台取不到文件。如果想自定义file的name属性，还是要和fileVal 配合使用。
                label: '添加文件',
                multiple:false    //默认为true，true表示可以多选文件，HTML5的属性
            },
            swf: '/CloudStorage/js/Uploader.swf',
            server: "/CloudStorage/fileOperation/fileUpload.action",
            duplicate:false,//是否可重复选择同一文件
            resize: false,
            formData: {		//向后台传递的参数列表
                "status":"file",
                "currentPath":"jiangchao",
                "uploadNum":"0000004730",    //"contentsDto.contentsId":"0000004730",
                "existFlg":'false'
            },  
            compress: null,//图片不压缩
            chunked: true,  //分片处理
            chunkSize: 2 * 1024 * 1024 * 1024, //每片2G
            chunkRetry:false,//如果失败，则不重试
            threads:1,//上传并发数。允许同时最大上传进程数。
            // runtimeOrder: 'flash',  
            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。  
            disableGlobalDnd: true
        });  
        // 当有文件添加进来的时候
    	uploader.on( "fileQueued", function( file ) {
            $list.append( "<div id='"+file.id+ "' class='item'>" +
        		   		 "<h4 class='info'>" + file.name + "</h4>" +
        		   		 "<p class='state'>等待上传</p>" +"</div>" );
            fileCount++;
            alert(fileCount);
    	});

       //当文件上传成功时触发。
       uploader.on( "uploadSuccess", function( file ) {
           var $li = $( '#'+file.id );
           $li.find('p.state').text('上传成功');
           //setTimeout(, 10000);
           /*$.delay(3000);
           window.location.reload();*/
       });
       //文件上传失败时触发
       uploader.on( "uploadError", function( file ) {
    	   var $li = $( '#'+file.id );
    	   $li.find('p.state').text('上传失败');
           uploader.cancelFile(file);
           uploader.removeFile(file,true);
           uploader.reset();
       });
       
       uploader.on( 'uploadProgress', function( file, percentage ) {
    	    var $li = $( '#'+file.id ),
    	        $percent = $li.find('.progress .progress-bar');
    	    // 避免重复创建
    	    if ( !$percent.length ) {
    	        $percent = $('<div class="progress progress-striped active">' +
    	          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
    	          '</div>'+'</div>').appendTo( $li ).find('.progress-bar');
    	    }
    	    $li.find('p.state').text('已上传:'+percentage.toFixed(2) * 100 + '%');
    	    
    	    $percent.css( 'width', percentage * 100 + '%' );
    	});
       
       //触发上传按钮
       $("#upload").on("click", function() {
           uploader.upload();
       });
       
       $("#remove").on("click", function() {
    	   alert(fileCount);
    	   var $li = $( '#WU_FILE_'+fileCount);
    	   $li.remove();
    	   if(fileCount!=-1)
    		   fileCount--;
       });
            
});
