package com.androidex.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

/**
 * 用于读取ini配置文件
 * @author USER
 *
 */
public class IniReader {
	
	//用于存放配置文件的属性值
	protected HashMap<String, Properties> sections = new HashMap<String, Properties>();
	private transient String currtionSecion;
	private transient Properties current;

    public IniReader()
    {
    }

    /**
	 * 读取文件
     * @param name the ini file name
	 * @throws IOException
	 */
	public IniReader(String name) throws IOException
	{
		InputStream in = new FileInputStream(name);
		InputStreamReader reader = new InputStreamReader(in, "UTF-8");
		BufferedReader read = null;
		try {
			if(reader != null)
			{
				read = new BufferedReader(reader);
                readAll(read);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileNotFoundException("文件不存在或者文件读取失败");
		}
	}
	
	/**
	 * 设置每次读取文件一行
	 * @param reader 文件流
	 * @throws IOException 
	 */
	protected void readAll(BufferedReader reader) throws IOException {
		String line = null;
		try {
			while((line = reader.readLine()) != null)
			{
				parseLine(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("文件内容读取失败");
		}
	}
	
	/**
	 * 获取ini文件的属性值
	 * @param line ini文件每行数据
	 */
	protected void parseLine(String line) {
		try {
			if (line != null) {
				line = line.trim();
				if (line.matches("\\[.*\\]")) {
					currtionSecion = line.replaceFirst("\\[(.*)\\]", "$1");
					current = new Properties();
					sections.put(currtionSecion, current);
				} else if (line.matches(".*=.*")) {
					if (current != null) {
						line = line.replace(" ", "");
						int i = line.indexOf('=');
						String name = line.substring(0, i);
						
						String value = line.substring(i+1,line.length());
						
						current.setProperty(name, value);
						
					}
				}
			}
		} catch (Exception e) {
            e.printStackTrace();
		}
	}
	
	/**
	 * 用于获取属性值的值
	 * @param section 整体属性的值
	 * @param name 属性值名字
	 * @return 属性值的值
	 */
	public String getValue(String section, String name)
	{

		Properties p = sections.get(section);
		
		if(p == null)
		{
			return null;			
		}
		String value = p.getProperty(name);
	
		return value;
	}

    /**
     *  获得段落的数量
     * @return  返回数量
     */
    public int getSectionCount()
    {
        return sections.size();
    }

    /**
     *  获得指定段落的参数数量
     * @param section   段落名
     * @return  返回数量
     */
    public int getPropertyCount(String section)
    {
        Properties p = sections.get(section);

        if(p == null)
        {
            return 0;
        }else{
            return p.size();
        }
    }
}
