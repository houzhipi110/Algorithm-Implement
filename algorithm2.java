package match;



	import java.util.Collections;
import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.PriorityQueue;
	import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.io.*;
	  

	public class algorithm2 {
		//for newwa
		//public static String[] [] Permute_Matrix = new String[720][6];
		//static String[][] expected_path_index={{"N2","N4"},{"N13","N14"}};
		static int branch_count=0;
		static int constraints;//约束3参数，点数不多于...
		static int Permute_num=1 ;
		public static int Row = 0;
		public static int Rowcount = 0;
		public static int total = 0;
		static int d =0;
		static List l = new ArrayList();
		static int Expected_node_num = 6;//指定节点个数输入参数
		static int Input_node_num = 18;//图总节点数
		//static String[] expected_nodes_input = {"N2","N4","N7","N12","N14","N13"};
		//static String[] mapping = {"S","N1","N2","N3","N4","N5","N6","N7","N8","N9","N10","N11","N12","N13","N14","N15","N16","E"};
		// static String[] mapping=new String[Input_node_num];
		 static String[] houzi = {"S","N1","N2","N3","N4","N5","N6","N7","N8","N9","N10","N11","N12","N13","N14","N15","N16","E"};
		
		// new way
		 //static List<String> out_put_path = new ArrayList<>();
		/* static int[] index_N={2,4,7,12,14,13};
		 static int[] index_M={2,4,14,13};
		 static List<Integer> list_index_N = new ArrayList<>();
		 static List<Integer> list_index_M = new ArrayList<>();
		 static List<Integer> delete_ordex = new ArrayList<>();*/
		 
		 //private static int distance;
		static List <Integer> Distance=new ArrayList<>();
		private static int last_choose;
		 //static String[] houzi_int = {"S","N1","N2","N3","N4","N5","N6","N7","N8","N9","N10","N11","N12","N13","N14","N15","N16","N17","N18","N19","N20","N21","N22","N23","N24","N25","N26","N27","N28","N29","N30","N31","N32","N33","N34","N35","N36","N37","N38","N39","N40","N41","N42","N43","N44","N45","N46","N47","N48","N49","E"};
		public static void main(String[] args) throws IOException {
			//new way define
			//int distance=0;
			//list_index_N.add(2);list_index_N.add(4);list_index_N.add(7);list_index_N.add(12);list_index_N.add(14);list_index_N.add(13);
			 //list_index_M.add(2);list_index_M.add(4);list_index_M.add(14);list_index_M.add(13);
			 // int[] index_N={2,4,7,12,14,13};
			 //int[] index_M={2,4,14,13};
			
			
			long startTime = System.currentTimeMillis();
			
			  List<Integer> list_index_N = new ArrayList<>();
			  List<Integer> list_index_M = new ArrayList<>();
			
			//===============================从txt中导入初始数据矩阵=================================================
			
			//Class hh=Classic_Dj.class;
			System.out.println("===========================正在导入邻接矩阵数据...============================== ");
			//InputStream is=hh.getClass().getResourceAsStream("/Dijstra/b.txt");   
	        //BufferedReader buf=new BufferedReader(new InputStreamReader(is)); 
			String filepath=System.getProperty("user.dir")+"/b.txt";
			
			System.out.println(filepath);
			
			File file=new File(filepath);
			FileReader f = new FileReader(file);
			BufferedReader buf = new BufferedReader(f);
			
			String line=null;
			String st =null;
			String st1 =null;
			String[] c1 =null;
			
			while((line=buf.readLine())!=null){
				
				st = line.replace("{", "");
				st1 = st.replace("}","");
				c1 = st1.split(",");
				int d = c1.length;
				int a [] = new int[d];
				for(int i=0;i<c1.length;i++){
				  a[i] = Integer.parseInt(c1[i]);
				  l.add(a[i]);
				}
			
			}
			Object[] o = l.toArray();
			int b1 [] = new int[o.length];
			for(int i=0;i<o.length;i++){
				String s = o[i].toString();
				b1[i] = Integer.parseInt(s);
				}
			
			int n =(int) Math.sqrt(o.length);//列
			int m = b1.length/n;//行
			int[][] adjMatrix = new int[m][n];
			int num1 = -1;

			for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
			num1++;
			adjMatrix[i][j] = b1[num1];
			}
			}
			//System.out.println(Arrays.deepToString(adjMatrix));
			System.out.println("===============================导入成功======================================");
			//===================================构造下标查询数组，供显示节点名称
			String[] houzi_int = new String[adjMatrix.length];
			houzi_int[0]="S";
			houzi_int[adjMatrix.length-1]="E";
			String conv_temp="";
			for(int i=1;i<adjMatrix.length-1;i++)
			{
				conv_temp=Integer.toString(i);
				houzi_int[i] = "N"+conv_temp;
			}
			
			//System.out.println(Arrays.toString(houzi_int));
			//String box="N"+"1";
			//System.out.println(box);
			
		//=============================================输入约束点矩阵===================================================
			
			 
			Scanner sc=new Scanner(System.in);
			System.out.println("===========输入必过节点的下标(一串整数并在输入时用英文逗号隔开)");
			System.out.println("========================输入参考：2,4,7,12,14,13============================"); 
			   String inputString=sc.next().toString();
			   
			   
			   String[] ss=inputString.split(",");
			   
			   int num123[]=new int[ss.length];
			   for(int i=0;i<ss.length;i++){
				   num123[i]=Integer.parseInt(ss[i]);
			    }
			    
			   Expected_node_num=ss.length;
			   String[] expected_nodes_input= new String[Expected_node_num];
			   for(int i=0;i<Expected_node_num;i++){
				   
				   list_index_N.add(num123[i]);
					 
				   
			   }
			   
			 //==============================必过路径的组合===================================
			   Scanner sx=new Scanner(System.in);
				System.out.println("======输入必过路径的组合的下标(一串整数并在输入时用英文逗号隔开)");
				System.out.println("===================输入参考：2,4,14,13=======================================");
				  
				    inputString=sx.next().toString();
				   
				   
				   String[] ssp=inputString.split(",");
				
				   int num1235[]=new int[ssp.length];
				   for(int i=0;i<ssp.length;i++){
					   num1235[i]=Integer.parseInt(ssp[i]);
				    }
				  
				   
				   for(int i=0;i<ssp.length;i++){
					   
					   list_index_M.add(num1235[i]);
						 
					   
				   }
				  
			   
			//=================================================输入通过节点的上限，约束2=============================================	
				   
				   
				   System.out.println("================ 约束2：  输入最多通过节点的数目限制");
				   Scanner in=new Scanner(System.in);
				   constraints=in.nextInt(); //接收整形数据 
			//==========================================输入并处理处理食蚁兽路径=============================== 
				   Scanner buzou=new Scanner(System.in);
					System.out.println("======输入有食蚁兽的路径端点下标");
					System.out.println("===================输入参考：11,12=======================================");
					  
					    inputString=buzou.next().toString();
					   
					   
					   String[] scanner_eatant=inputString.split(",");
					   
					   
					   
							   int [][] eatant_index=new int[(scanner_eatant.length)/2][2];
					   for(int i=0;i<scanner_eatant.length;i++){
						   
						   if(i%2==0){eatant_index[i/2][0]=Integer.parseInt(scanner_eatant[i]);}
						   else{eatant_index[i/2][1]=Integer.parseInt(scanner_eatant[i]);}
						   
					    }
					    
					   for(int i=0;i<(scanner_eatant.length)/2;i++)
					   {
						   
						   
							   adjMatrix[eatant_index[i][0]][eatant_index[i][1]]=65535;
							   adjMatrix[eatant_index[i][1]][eatant_index[i][0]]=65535;
						   
					   }
				   
			//===========================================初始化映射==============================================
			Input_node_num=adjMatrix.length;
			String[] mapping=new String[Input_node_num];
			 for(int i=0;i<Input_node_num-1;i++)
			 {
				 mapping[i]=houzi_int[i];
			 }
			 mapping[Input_node_num-1]="E";
			 
			
			List<String> list_mapping=Arrays.asList(mapping); //转化为Arraylist
			//生成排列种类个数
			/*for (int i=Expected_node_num;i>0;i--){
				Permute_num = Permute_num * i;
			}*/
			
			//String[][] Permute_Matrix = new String[Permute_num][Expected_node_num];
			
			// arrange(expected_nodes_input, 0, expected_nodes_input.length,Permute_Matrix);//求排列遍历Permute_Matrix[i][j]
			 
		//===========================================生成虚拟图表对象=====================================	
			Graphs8 g = new Graphs8();
			
			for(int i=0;i<mapping.length;i++){
				List vex_temp = new ArrayList();
				vex_temp.clear();

				for(int j=0;j<mapping.length;j++){
	
					if((adjMatrix[i][j]!=0)&&(adjMatrix[i][j]!=65535)){
						vex_temp.add(new Vertexs8(mapping[j],adjMatrix[i][j]));
					}
				}
				int sizee=vex_temp.size();
				Vertexs8[] haha=(Vertexs8[])vex_temp.toArray(new Vertexs8[sizee] );
				g.addVertex(mapping[i], Arrays.asList(haha));
			}
			

			//=======================================对指定点进行排列组合输出========================================
			int Compare_min_nodes=65535;
			int counts=0;
			int min=65535;
			int min_nodes=65535;
			int min_distance_nodes=65535;
			int min_nodes_distance=65535;
			List min_list=new ArrayList();//存储花费最小表
			List min_nodes_list=new ArrayList();//储存点数最小表
			List temp_list=new ArrayList();//linshi储存
			List <Integer> temp_index=new ArrayList<Integer>();
			
			//final list_index_N,
			//final list_index_M
			
			 List<String> out_put_path = new ArrayList<>();
			
			speedupDJ ( list_index_N, list_index_M,out_put_path,Expected_node_num,0, g, houzi_int,  list_mapping,  adjMatrix);
			long endTime = System.currentTimeMillis();
			System.out.println("总共花费的时间："+(endTime-startTime)+"ms");
			
			
		}



		
		//方法
		public static	void speedupDJ ( List<Integer> list_index_N, List<Integer> list_index_M,List<String> out_put_path,int temp_num,int StartIndex,Graphs8 g,String [] houzi_int, List<String> list_mapping, int[][] adjMatrix){
			 List <Integer> Distance=new ArrayList<>();
			 List<String> temp_out_put_path=new ArrayList<>();
			 temp_out_put_path.addAll(out_put_path);
			 int distance=0;
			 int temp_start=0;
			 List<Integer> list_index_NN=new ArrayList<>();
			  list_index_NN.addAll(list_index_N);
			 int temptemptemp_num=temp_num;
			temp_start=StartIndex;
			 //求出当前start点到其他点的距离存在Distance中
			for(int i=0;i<temptemptemp_num;i++)   //temp_num必过点的个数
			{
				//houzi_int[StartIndex];
				List<String> Shortpath=g.getShortestPath(houzi_int[temp_start],houzi_int[list_index_NN.get(i)]);
				Shortpath.add(houzi_int[temp_start]);
				Collections.reverse(Shortpath);
				distance=Short_Distance(list_mapping,adjMatrix, Shortpath);
				Distance.add(distance);
				
			}
			
			List <Integer> distance_index=new ArrayList<>();
			int min_temp=Distance.get(0);//暂时把第一个点当成最小距离
			//遍历距离list将最小的距离下标存入集合distance_index中
			for(int i=0;i<temptemptemp_num;i++)
			{
				if(Distance.get(i)<=min_temp)
				{
					if(Distance.get(i)<min_temp)
					{	
						distance_index.clear();
						distance_index.add(i);
						min_temp=Distance.get(i);
					}
					else
					{
						distance_index.add(i);
					}
				}
			}
			Distance.clear();
			//由于两点间最近距离可能有多种走法i
			List <Integer>temp_list_index_N=new ArrayList<>();
			int temp_temp_num=0;
			int flag_path=0;//路径标志位
			
			for(int i=0;i<distance_index.size();i++)
			{		
					temp_out_put_path.clear();
					temp_out_put_path.addAll(out_put_path);
					temp_start=StartIndex;
					list_index_NN.clear();
					list_index_NN.addAll(list_index_N);
					temptemptemp_num=temp_num;
					List<String> Shortpath=g.getShortestPath(houzi_int[temp_start],houzi_int[list_index_NN.get(distance_index.get(i))]);
					
					//Shortpath.add(houzi_int[StartIndex]);
					Collections.reverse(Shortpath);
					temp_out_put_path.addAll(Shortpath);
					Shortpath.clear();
					
					temp_start=list_index_NN.get(distance_index.get(i));//把距离最近的点作为下一次的起点
					//delete_ordex.add(StartIndex);
					int box=distance_index.get(i);
					list_index_NN.remove(box);//在list-N中删除已经作为起始点的点
					temptemptemp_num--;//第一次输出后temp_num已经变为0
					int index=list_index_M.indexOf(temp_start);
					//查找改点是否在M（路径集合）中出现，出现则直接走必过路径到达下一点.
					if(index!=-1)
					{   
						
						temptemptemp_num--;
						if(index%2==0)
						{	
							temp_out_put_path.add("N"+list_index_M.get(index+1));
							//box=list_index_M.get(index+1);
							list_index_NN.remove(list_index_M.get(index+1));
							temp_start=list_index_M.get(index+1);
							//delete_ordex.add(StartIndex);
							
						}
						else
						{	
							temp_out_put_path.add("N"+list_index_M.get(index-1));
							//box=list_index_M.get(index-1);
							list_index_NN.remove(list_index_M.get(index-1));
							temp_start=list_index_M.get(index-1);
							//delete_ordex.add(StartIndex);
							
						}
					}
					
					
					
					//当temp_num为0说明必过点已经走完，则直接让最后一点必过点直接找终点的最近距离
						if(temptemptemp_num==0)
						{
							 last_choose=temp_start;
							 Collections.reverse(temp_out_put_path);
							 temp_out_put_path.add("S");
								List <String>arr =g.getShortestPath(houzi_int[last_choose], "E");
								arr.addAll(temp_out_put_path);
								Collections.reverse(arr);
								//System.out.println(delete_ordex);
								if(arr.size()<constraints)
								{
									System.out.println("最优路径="+arr);
									//Short_Distance(list_mapping, adjMatrix, arr);
									System.out.println("最优花费="+Short_Distance(list_mapping, adjMatrix, arr));
									System.out.println("当前节点数="+arr.size());
								}
								
								
						}
						else
						{	
							speedupDJ ( list_index_NN,list_index_M,temp_out_put_path,temptemptemp_num, temp_start, g, houzi_int,  list_mapping,  adjMatrix);
						}
				
				
				
				
			}
			/*List<String> Shortpath=g.getShortestPath(houzi_int[StartIndex],houzi_int[list_index_N.get(distance_index)]);
			//Shortpath.add(houzi_int[StartIndex]);
			Collections.reverse(Shortpath);
			out_put_path.addAll(Shortpath);
			Shortpath.clear();
			
			StartIndex=list_index_N.get(distance_index);
			delete_ordex.add(StartIndex);
			
			list_index_N.remove(distance_index);
			temp_num--;
			int index=list_index_M.indexOf(StartIndex);
			if(index!=-1)
			{
				temp_num--;
				if(index%2==0)
				{	
					out_put_path.add("N"+list_index_M.get(index+1));
					list_index_N.remove(list_index_M.get(index+1));
					StartIndex=list_index_M.get(index+1);
					delete_ordex.add(StartIndex);
					
				}
				else
				{	
					out_put_path.add("N"+list_index_M.get(index-1));
					list_index_N.remove(list_index_M.get(index-1));
					StartIndex=list_index_M.get(index-1);
					delete_ordex.add(StartIndex);
					
				}
			}
			
			if(temp_num==0)
			{
				 last_choose=StartIndex;
			}
			else
			{
				speedupDJ ( temp_num, StartIndex, g, houzi_int,  list_mapping,  adjMatrix);
			}
			*/
			
		}
		
		
		
		private static List Expected_Points_Short_Path_Print(List<String> list_mapping, Graphs8 g, int[][] adjMatrix,String Point1,String[] expected_nodes_input,String Point8) {
			int P_length = expected_nodes_input.length;
			List<String> a = g.getShortestPath(Point1, expected_nodes_input[0]);
			
			List<String> first=new ArrayList<String>();
			first=a;
			first.add("S");
			Collections.reverse(first);
			List<String> last=new ArrayList<String>();
			for(int i=0;i<(P_length-1);i++){
				
				 last=g.getShortestPath(expected_nodes_input[i], expected_nodes_input[i+1]);
				 Collections.reverse(last);
				 first.addAll(last);
			}
			List<String> j = g.getShortestPath(expected_nodes_input[(P_length-1)], Point8);
			Collections.reverse(j);
			first.addAll(j);
			return first;
		
		}
		
		
		
		private static int Short_Distance(List<String> list_mapping,int[][] adjMatrix,List<String> arr) {
		
		int size = arr.size(); 
		String[] Narr = (String[])arr.toArray(new String[size]);//转化为数组
		int[] Index=new int[size];
		for (int i=0;i<size;i++){
			Index[i] = list_mapping.indexOf(Narr[i]);
		}
		
		
		int sum=0;
		for (int i=1;i<size;i++){
			
			sum=sum+adjMatrix[Index[i-1]][Index[i]];
			
			}
		
		return sum;
		}
		
		
		
		public static void swap(String[] str, int i, int j)
		{
			String temp = new String();
			temp = str[i];
			str[i] = str[j];
			str[j] = temp;
		}
		public static void arrange (String[] str, int st, int len,String[][] Permute_Matrix )
		{
			if (st == len - 1)
			{
				for (int i = 0; i < len; i ++)
				{	Rowcount++;
					//System.out.print(str[i]+ "  ");
					
					Permute_Matrix[Row][i]=str[i];
				}
				
				//System.out.println();
				Row++;
				total++;
			}
			else
			{
				for (int i = st; i < len; i ++)
				{
					swap(str, st, i);
					arrange(str, st + 1, len,Permute_Matrix);
					swap(str, st, i);
				}
			}
			
		}
		
	}

	class Vertexs8 implements Comparable<Vertexs8> {
		
		private String id;
		private Integer distance;
		
		public Vertexs8(String id, Integer distance) {
			super();
			this.id = id;
			this.distance = distance;
		}

		public String getId() {
			return id;
		}

		public Integer getDistance() {
			return distance;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setDistance(Integer distance) {
			this.distance = distance;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((distance == null) ? 0 : distance.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vertexs8 other = (Vertexs8) obj;
			if (distance == null) {
				if (other.distance != null)
					return false;
			} else if (!distance.equals(other.distance))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Vertex [id=" + id + ", distance=" + distance + "]";
		}

		@Override
		public int compareTo(Vertexs8 o) {
			if (this.distance < o.distance)
				return -1;
			else if (this.distance > o.distance)
				return 1;
			else
				return this.getId().compareTo(o.getId());
		}
		
	}

	class Graphs8 {
		
		private final Map<String, List<Vertexs8>> vertices;
		
		public Graphs8() {
			this.vertices = new HashMap<String, List<Vertexs8>>();
		}
		
		public void addVertex(String character, List<Vertexs8> vertex) {
			this.vertices.put(character, vertex);
		}
		 
		@Override
		public String toString() {
			return "Graph [vertices=" + vertices + "]";
		}

		public List<String> getShortestPath(String start, String finish) {
			final Map<String, Integer> distances = new HashMap<String, Integer>();
			final Map<String, Vertexs8> previous = new HashMap<String, Vertexs8>();
			PriorityQueue<Vertexs8> nodes = new PriorityQueue<Vertexs8>();
			
			for(String vertex : vertices.keySet()) {
				if (vertex == start) {
					distances.put(vertex, 0);
					nodes.add(new Vertexs8(vertex, 0));
					//测试
					//System.out.println("vertex="+vertex);
					//
				} else {
					distances.put(vertex, Integer.MAX_VALUE);
					nodes.add(new Vertexs8(vertex, Integer.MAX_VALUE));
				}
				//测试
				//System.out.println("vertex="+vertex);
				//
				previous.put(vertex, null);
			}
			
			while (!nodes.isEmpty()) {
				Vertexs8 smallest = nodes.poll();
				if (smallest.getId() == finish) {
					final List<String> path = new ArrayList<String>();
					while (previous.get(smallest.getId()) != null) {
						path.add(smallest.getId());
						smallest = previous.get(smallest.getId());
					}
					return path;
				}

				if (distances.get(smallest.getId()) == Integer.MAX_VALUE) {
					break;
				}
							
				for (Vertexs8 neighbor : vertices.get(smallest.getId())) {
					Integer alt = distances.get(smallest.getId()) + neighbor.getDistance();
					if (alt < distances.get(neighbor.getId())) {
						distances.put(neighbor.getId(), alt);
						previous.put(neighbor.getId(), smallest);
						
						forloop:
						for(Vertexs8 n : nodes) {
							if (n.getId() == neighbor.getId()) {
								nodes.remove(n);
								n.setDistance(alt);
								nodes.add(n);
								break forloop;
							}
						}
					}
				}
			}
			
			return new ArrayList<String>(distances.keySet());
		}
		
		
	}






