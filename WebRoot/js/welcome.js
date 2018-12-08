var filename;//文件当前路径
var filename1;//用户初始路径
var copypath;//被复制文件路径
var copyname;//被复制文件名
var filetitle=new Array();//文件标题地址
var filetitlemore=new Array();//文件标题名
var num=1;//文件名的id
var count=1;//复制剪切判断符；复制为1，剪切为2；
var childfilename=new Array();//文件夹内的子文件名数组，用以判断文件名重复
var fileCount = -1;//正在上传的文件数

$(document).ready(function(){
	/**
	 * 左侧剩余文件容量进度条设置
	 */
	var usedMemory=(((2048-Number(document.getElementById("usedMemory").value))/2048)*100).toString()+"%";
    document.getElementById("remainingSpaceUi_span").style.width=usedMemory;

	/**
	 * 初始化文件路径
	 */
	var currentpath=document.getElementById("currentpath");
	if(filename==null){
		filename=currentpath.value;
		filename1=currentpath.value;
		filetitle[0]=filename;
		filetitlemore[0]=filename;
	}
	/**
	 * 运行显示文件方法
	 */
	showFileByName(filename);    	
});
/**
 * 根据文件类型显示文件
 */
function showFileByType(type){
    $("#table_body").empty(); 
    $("#file_title1").empty();
        $.ajax({
            type : "POST",
            url : "/CloudStorage/fileOperation/_kindFiles",
            async : false,
            data : {fileType:type},
            success : function(data) {
                  adddiv(data);
                },
            dataType : "json"
        });
        disableHref();
}
/**
 * 根据文件路径显示文件
 */
function showFileByName(fileString) {
    $("#table_body").empty();
   var num=filetitle.length;
   var is1=0;
    for(var i=0;i<filetitle.length;i++){   	
        if(filetitle[i]==fileString){
            filetitle.length=i+1;
            filetitlemore.length=i+1;
            filename=filetitle[i];
            if(i==0||i==filetitle.length-1){
                is1=1;
            }
        }
    }  
    if(num==filetitle.length&&is1==0){
        filename +="/"+fileString;
        filetitle.push(filename);
        filetitlemore.push(fileString);
    }
   
    $("#file_title1").empty();
    for(var i=0;i<filetitle.length;i++){
        if(i==0){
            $("#file_title1").append(
                "<span><a class=\"file_title1\" onclick=\"showFileByName('"+filename1+"')\">我的云盘</a></span>"
            );
        }else if(i==filetitle.length-1){
            $("#file_title1").append(
                "<span> >"+filetitlemore[i]+"</span>"
            );
        }else {
            $("#file_title1").append(
                "<span><a onclick=\"showFileByName('"+filetitle[i]+"')\"> >"+filetitlemore[i]+"</a></span>"
            );
        }
    }
    $.ajax({     
        type : "POST",
        url : "/CloudStorage/fileOperation/_showFiles.action",
        async : false,
        data : {currentPath:filename},
        success : function(data) {
            adddiv(data);
        },
        dataType : "json"
    });
    showHref();
    e.stopPropagation();
}
/**
 * 添加文件信息
 */
function adddiv(data){
	childfilename.length=0;
    $.each(data, function(index,element) {
    	addType(element);

    });
    /**
	 * 设置右选框菜单
	 */
    $('.table_body_dd').contextMenu('myMenu1',{
    	bindings:{
    		'open1': function(t){
    		    $("#shareModal").modal('show');
    			var fileurl=t.getElementsByTagName("p")[0].innerHTML;
    			var filetype=t.getElementsByTagName("h4")[0].innerHTML;	
    			filePreview(fileurl,filetype);
            },
            'rename1': function(t){
                  var tx=t.getElementsByTagName("a")[0].innerHTML;
                  var tz=t.getElementsByClassName("text")[0].id;
                  var f="#"+tz;
                  $(f).empty();
                  $(f).prepend(
                      "<form class='new_folder_form'>"
                      +"<input class='new_folder_name' id='new_folder_name' type='text' value='"+tx+"'>"
                      +"<input type='button' class='new_folder_submit' onclick=\"rename('"+tx+"')\"  value='确认'>"
                      +"<input type='button' class='new_folder_cancel' onclick=\"cancelrename('"+f+"','"+tx+"')\" value='取消'>"
                      +"</form>"
                  );
              },
              'delete1': function(t){
            	var tx=t.getElementsByTagName("a")[0].innerHTML;
            	deletefile(tx);
              },
              'copy1': function(t){
              	var tx=t.getElementsByTagName("a")[0].innerHTML;
              	copypath=filename;
              	copyname=tx;
              	count=1;
              },
              'cut1': function(t){
                 var tx=t.getElementsByTagName("a")[0].innerHTML;
                 copypath=filename;
                 copyname=tx;
                 count=2;
               },
               'download1': function(t){
                   var downloadPath=t.getElementsByTagName("p")[0].innerHTML;
                   window.location.href="/CloudStorage/fileOperation/fileDownload.action?filePath="+downloadPath;
                 },
               'share1': function(t){
                   var sharePath=t.getElementsByTagName("h2")[0].innerHTML;
                   alert(sharePath);
                 }
        }
    });
    $('.table_body_dd1').contextMenu('myMenu2',{
        bindings:{
        	'open2': function(t){
        		filename +="/"+t.getElementsByTagName("a")[0].innerHTML;
        		filetitle.push(filename);
                filetitlemore.push(t.getElementsByTagName("a")[0].innerHTML);
        		showFileByName(filename);
            },
            'rename2': function(t){
                  var tx=t.getElementsByTagName("a")[0].innerHTML;
                  var tz=t.getElementsByClassName("text")[0].id;
                  var f="#"+tz;
                  $(f).empty();+
                  $(f).prepend(
                      "<form class='new_folder_form'>"
                      +"<input class='new_folder_name' id='new_folder_name' type='text' value='"+tx+"'>"
                      +"<input type='button' class='new_folder_submit' onclick=\"rename('"+tx+"')\"  value='确认'>"
                      +"<input type='button' class='new_folder_cancel' onclick=\"cancelrename('"+f+"','"+tx+"')\" value='取消'>"
                      +"</form>"
                  );
              },
              'delete2': function(t){
            	var tx=t.getElementsByTagName("a")[0].innerHTML;
            	deletefile(tx);
              },
              'copy2': function(t){
              	var tx=t.getElementsByTagName("a")[0].innerHTML;
              	copypath=filename;
              	copyname=tx;
              	count=1;
              },
              'cut2': function(t){
                 var tx=t.getElementsByTagName("a")[0].innerHTML;
                 copypath=filename;
                 copyname=tx;
                 count=2;
               },
               'share2': function(t){
                   var sharePath=t.getElementsByTagName("h3")[0].innerHTML;
                 }
        }
    });
    $('.main-content').contextMenu('myMenu3',{
        bindings:{
        	'paste': function(t){
            	pastefile();
              },
             'refresh': function(t){
            	 showFileByName(filename);
              }
        }
    });
}
/**
 * 取消文件重命名
 */
function cancelrename(f,tx) {
    $(f).empty();
    $(f).prepend(
      "<a ondblclick=\"showFileByName('"+tx+"')\" id='sss' class='file_body' title='"+tx+"'>"+tx+"</a>"
    )
}
/**
 * 文件重命名
 */
function rename(oldname){
	var inputValue=document.getElementById("new_folder_name").value;
	
	 $.ajax({     
	        type : "POST",
	        url : "/CloudStorage/fileOperation/_changeFileName.action",
	        async : true,
	        data : {currentPath:filename,oldName:oldname,newName:inputValue},
	        success : function(data) {
                  showFileByName(filename);
	        },
	        dataType : "json"
	    });
}
/**
 * 删除文件
 */
function deletefile(tx){
	
	 $.ajax({     
	        type : "POST",
	        url : "/CloudStorage/fileOperation/_deleteFile.action",
	        async : true,
	        data : {currentPath:filename,target_file:tx},
	        success : function(data) {
                  showFileByName(filename);
	        },
	        dataType : "json"
	    });
}
/**
 * 粘贴文件
 */
function pastefile(tx){
	if(comparename(copyname)){
		alert("该文件已存在！");
	}else{
		if(count==1){
	   	   $.ajax({     
	   		   type : "POST",
	   		   url : "/CloudStorage/fileOperation/_copyFileOrDir.action",
	   		   async : true,
	   		   data : {currentPath:copypath,des_dir:filename,target_file:copyname},
	   		   success : function(data) {
	   			   showFileByName(filename);
	   		   },
		        dataType : "json"
	   	   });
	  	}else if(count==2){
		  $.ajax({     
			  type : "POST",
			  url : "/CloudStorage/fileOperation/_cutFileOrDir.action",
			  async : true,
			  data : {currentPath:copypath,des_dir:filename,target_file:copyname},
			  success : function(data) {
				  showFileByName(filename);
			  },
		        dataType : "json"
		    });
	   }
    }
	
}
/**
 * 添加文件条
 */
function addType(element){
	var str1="<dd class='table_body_dd' onmousedown=\"filePreview('"+element.fileurl+"','"+element.filetype+"')\" >"+"<div class='file-name'>";
	var str4="<dd class='table_body_dd1' ondblclick=\"showFileByName('"+element.filename+"')\" >"+"<div class='file-name'>";
	var str2= "</div>"+"</div>"+"<div class='file-size'>"+"<span class='text'>"+element.filesize+"</span>"+"</div>"+"<div class='file-time'>"
               +"<span class='text'>"+element.filedate+"</span>"+"</div>"+"</dd>";
	var str3=  "<div class='text' id='"+num+"'>"+"<a  class='file_body' data-toggle='modal' data-target='#shareModal' title='"+element.filename+"'>"+element.filename+"</a>"+
	            "<p class='filepathonserverClass' hidden>"+element.filepathonserver+"</P>";
	childfilename.push(element.filename);
	if(element.filetype=='dir'){
		$("#table_body").append(
	             str4
	             +"<div class='table_body_Folder'></div>"
	             +"<div class='text' id='"+num+"'>"
	             +"<h3 hidden>"+element.fileurl+"</h3>"
	             +"<a class='file_body' title='"+element.filename+"'>"+element.filename+"</a>"
	             +str2
	     );
		num++;
	}else{
		var typeImage="/CloudStorage/images/"+element.filetype+".png";
		$("#table_body").append(
	             str1
	             +"<div style=\"position:relative;float:left;width:26px;height:26px;margin-top:10px;margin-left:25px;background:url("+typeImage+")no-repeat\"></div>"
	             +str3
	             +"<h2 hidden>"+element.fileurl+"</h2>"
	             +"<h4 hidden>"+element.filetype+"</h4>"
	             +str2
	     );
		num++;
	}
}


/**
 *新建文件夹界面操作 
 */
function addnewfolder(){
    var myDate = new Date();
    $("#table_body").prepend(
        "<dd class='table_body_dd2'>"
        +"<div class='file-name'>"
        +"<div class='table_body_Folder'></div>"
        +"<div class='text'>"
        +"<form class='new_folder_form'>"
        +"<input class='new_folder_name' id='new_folder_name' type='text' value='新建文件夹'>"
        +"<input type='button' class='new_folder_submit' onclick='newfolder()' value='确认'>"
        +"<input type='button' class='new_folder_cancel' onclick='cancelnewfolder()' value='取消'>"
        +"</form>"
        +"</div>"
        +"</div>"
        +"<div class='file-size'>"
        +"<span class='text'>--</span>"
        +"</div>"
        +"<div class='file-time'>"
        +"<span class='text'>"+myDate.toLocaleString( )+"</span>"
        +"</div>"
        +"</dd>"
    );
    disableHref();
}
/**
 * 新建文件夹后台交互
 */
function newfolder(){
	var inputValue=document.getElementById("new_folder_name").value;
	if(comparename(inputValue)){
		alert("该文件夹已存在！");
	}else{
		 $.ajax({     
		        type : "POST",
		        url : "/CloudStorage/fileOperation/_createDir.action",
		        async : true,
		        data : {currentPath:filename,dirName:inputValue},
		        success : function(data) {
		        	$("#table_body").empty();
		        	 showHref();
		            adddiv(data);
		        },
		        error:function() {
		            alert("创建失败，存在同名文件夹！");
		        },
		        dataType : "json"
		    });
		 cancelnewfolder();
	}
}
/**
 * 取消新建文件夹
 */
function cancelnewfolder(){
    $("#table_body").children(":first").remove();
    showHref();
}
/**
 * 设置超链接，按钮不可用
 */
function  disableHref(){
    var hrefDom = document.getElementById("new_files");
    if(hrefDom.className=='new_files'){
        hrefDom.className=hrefDom.className.replace("new_files","new_files1");
        var hrefDom1 = document.getElementById("myMenu");
        hrefDom1.className=hrefDom1.className.replace("","mymenu1_1");
        document.getElementById("myMenu").disabled=true;
        document.getElementById("new_files").disabled=true;
    }

}
/**
 * 设置超链接按钮可用
 */
function showHref(){
    var hrefDom = document.getElementById("new_files");
    if(hrefDom.className=='new_files1'){
        hrefDom.className=hrefDom.className.replace("new_files1","new_files");
        var hrefDom1 = document.getElementById("myMenu");
        hrefDom1.className=hrefDom1.className.replace("mymenu1_1","");
        document.getElementById("myMenu").disabled=false;
        document.getElementById("new_files").disabled=false;
    }

}
/**
 * 页面刷新
 */
function refresh(){
	$("#jqContextMenu").hide();
	$("#shadowid").hide();
	showFileByName(filename);	
}
/**
 * 文件预览
 */
function openfile(){
	
} 
/**
 * 比较文件名是否有重复
 */
function comparename(newname){
	for(var i=0;i<childfilename.length;i++)
    {
		if(newname==childfilename[i]){
			return 1;
		}
	}
	return 0;
}
/**
 * 文件上传
 */
function fileUpload() {
    $("#upload").show();
    $('#thelist').empty();
   // alert(filename);
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
                "currentPath":filename,
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
    		$('#thelist').empty();
            $list.append( "<div id='"+file.id+ "' class='item list-group'>" +
        		   		 "<p class='info list-group-item'>文件名： " + file.name + "</p>" +
        		   		 "<p class='state list-group-item'>文件状态：等待上传</p>" +"</div>" );
            fileCount++;
            //alert(fileCount);
    	});

       //当文件上传成功时触发。
//       uploader.on( "uploadSuccess", function( file ) {
//           var $li = $( '#'+file.id );
//           $li.empty();
//          // $("#upload").hide();
//           //setTimeout(, 10000);
//           /*$.delay(3000);
//           window.location.reload();*/
//       });
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
    	    if(percentage==1.0){
    	    	$li.empty();
    	    	$li.append("<p style='position:relative;margin-top:50px;font-size:20px;text-align:center;color:green;'>上传成功！</p>")
    	    }
    	}); 
       //触发上传按钮
       $("#upload").on("click", function() {
           uploader.upload();  
       });
       
       $("#remove").on("click", function() {
    	   $('#thelist').empty();
       });
}
/**
 * 取消文件上传
 */
function cancelUpload() {
    $("#upload").hide();
    showFileByName(filename);
}
/**
 * 文件预览操作
 */
function filePreview(fileurl,filetype){		
	$('#filePreview').empty();
	if(filetype=='jpg'||filetype=='png'||filetype=='gif'){
		$('#filePreview').append(
				"<img src='"+fileurl+"'style='position:relative;max-width:900px;max-height:600px;margin:auto;'>"
		);		
	}else if(filetype=='txt'){
		
		$('#filePreview').append(
				"<iframe src='"+fileurl+"' style='position:relative;max-width:900px;max-height:600px;margin:auto;width:100%;height:700px;'>"
				+"</iframe>"
		);	
	}else if(filetype=='mp4'||filetype=='avi'||filetype=='wmv'){
		
		$('#filePreview').append(
				"<embed src='"+fileurl+"' autoplay='autoplay' style='position:relative;max-width:900px;max-height:600px;margin:auto;width:100%;height:700px;'>"
				+"</embed>"
		);	
	}else if(filetype=='mp3'||filetype=='wav'||filetype=='mid'){
		
		$('#filePreview').append(
				"<embed src='"+fileurl+"' autoplay='autoplay' style='position:relative;max-width:900px;max-height:600px;margin:auto;width:100%;height:700px;'>"
				+"</embed>"
		);	
	}else{
		$('#filePreview').append(
				"<h2 style='position:relative;color:red;top:100px'>该文件类型暂不支持预览！</h2>"
		);	
	}
}




