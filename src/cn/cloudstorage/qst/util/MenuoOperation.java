package cn.cloudstorage.qst.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MenuoOperation {
	/**
	 * API list
	 * changeFileName(String currentPath,String oldName,String newName)		修改文件名
	 * copyFileOrDir(String currentPath, String des_dir, String target_file)复制文件
	 * deleteFile(String currentPath,String target_file)					删除文件
	 * cutFileOrDir(String currentPath, String des_dir,String target_file)	剪切文件
	 * createDir(String currentPath, String dirName)						创建文件夹
	 */
	//创建文件夹
	public static boolean createDir(String currentPath, String dirName){
		if(currentPath==null||"".equals(currentPath)){
			return false;
		}
		File file=new File(currentPath+File.separator+dirName);
		if(!file.exists()){file.mkdirs();}//如果不存在则创建
		return true;
	}
	//重命名文件和文件夹
	public static boolean changeFileOrDirName(String currentPath,String oldName,String newName){
		if((oldName==null||"".equals(oldName))&&(newName==null||"".equals(newName))){
			return false;
		}
		File oldfile=new File(currentPath+File.separator+oldName);
		File newfile=new File(currentPath+File.separator+newName);
		if(!oldfile.exists()){
			return false;
		}
		return oldfile.renameTo(newfile);
	}
	//剪切文件及文件夹
	public static boolean cutFileOrDir(String currentPath, String des_dir,String target_file){
		try {
			//这里不用判断目的地文件夹是否存在，copyFileOrDir()方法执行时内部会自动进行判断
			File file=new File(currentPath+File.separator+target_file);
			if(!file.exists()){
				return false;
			}
			copyFileOrDir(currentPath, des_dir, target_file); 	//复制文件
			return deleteFileOrDir(currentPath, target_file);	//删除文件
		} catch (Exception e) {
			throw new RuntimeException(e+"剪切文件异常");
		}
	}
	/**
	 * currentPath   当前所在路径
	 * des           目标目录
	 * target_Dir    目标文件或文件夹
	 */
	//拷贝文件和文件夹-----1、File-->Dir     2、Dir-->Dir
	public static void copyFileOrDir(String currentPath, String des_dir, String target_file){
		String src=currentPath+File.separator+target_file;
		File src_file=new File(src);
		if(!src_file.isDirectory()){
			copyFileOrDir(src, des_dir);
			return;
		}
		des_dir=des_dir+File.separator+target_file;
		File des_file=new File(des_dir);
		if(!des_file.exists()){des_file.mkdirs();}
		copyFileOrDir(src, des_dir);
	}
	//拷贝文件和文件夹
	@Deprecated
	public static void copyFileOrDir(String src, String des){
        File file1=new File(src);
        File file2=new File(des);
        File[] fs=file1.listFiles();//如果是文件，则返回null；如果是文件夹，则返回文件列表
        if(!file1.exists()){ return; }//如果源文件不存在，则直接退出
        if(!file2.exists()){ file2.mkdirs(); }//如果目标文件夹不存在，则创建
        if(fs==null){//如果是文件的话,直接复制文件
        	copyFile(src, file2.getPath()+File.separator+file1.getName());
        	return;
        }
        for (File f : fs) {
            if(f.isFile()){
            	copyFile(f.getPath(),des+File.separator+f.getName());//调用文件拷贝的方法
            }else if(f.isDirectory()){
            	copyFileOrDir(f.getPath(),des+File.separator+f.getName());
            }
        }
    }  
	//拷贝文件
	private static void copyFile(String oldPath, String newPath)  {
		FileOutputStream out=null;
		FileInputStream in=null;
		try {
			File oldFile = new File(oldPath);
	        File file = new File(newPath);
	        in = new FileInputStream(oldFile);
	        out = new FileOutputStream(file);
	        byte[] buffer=new byte[1024*1024];
	        while((in.read(buffer)) != -1){
	            out.write(buffer);
	        }
		} catch (Exception e) {
			throw new RuntimeException(e+"文件拷贝异常");
		}finally{
			try {
				if(out!=null) out.close();
				if(in!=null) in.close();
			} catch (IOException e) {
				throw new RuntimeException(e+"输入输出流关闭异常");
			}
		}
    }
	//删除文件或文件夹
	public static boolean deleteFileOrDir(String currentPath,String target_file){
		if(target_file==null||"".equals(target_file)){
			return false;
		}
		File file=new File(currentPath+File.separator+target_file);
		if(!file.exists()){
			return false;
		}
		return deleteDirAndChildren(file);
	}
	//删除文件和文件夹----递归方法----被调用
	private static boolean deleteDirAndChildren(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDirAndChildren(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();// 目录此时为空，可以删除
	}
	
}
