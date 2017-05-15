


	import java.util.Collections;
import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.PriorityQueue;
	import java.util.Scanner;
	import java.io.*;
	  

	public class Classic_Dj {
		
		//public static String[] [] Permute_Matrix = new String[720][6];
		//static String[][] expected_path_index={{"N2","N4"},{"N13","N14"}};
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
		 //static String[] houzi_int = {"S","N1","N2","N3","N4","N5","N6","N7","N8","N9","N10","N11","N12","N13","N14","N15","N16","N17","N18","N19","N20","N21","N22","N23","N24","N25","N26","N27","N28","N29","N30","N31","N32","N33","N34","N35","N36","N37","N38","N39","N40","N41","N42","N43","N44","N45","N46","N47","N48","N49","E"};
		public static void main(String[] args) throws IOException {
			
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
				   
						   expected_nodes_input[i]=houzi_int[num123[i]];
					 
				   
			   }
			 //==============================必过路径的组合===================================
			   Scanner sx=new Scanner(System.in);
				System.out.println("======输入必过路径的组合的下标(一串整数并在输入时用英文逗号隔开)");
				System.out.println("===================输入参考：2,4,14,13=======================================");
				  
				    inputString=sx.next().toString();
				   
				   
				   String[] ssp=inputString.split(",");
				   String[][] expected_path_index=new String[ssp.length/2][2];
				   
				   int num1234[][]=new int[(ssp.length)/2][2];
				   for(int i=0;i<ssp.length;i++){
					   
					   if(i%2==0){num1234[i/2][0]=Integer.parseInt(ssp[i]);}
					   else{num1234[i/2][1]=Integer.parseInt(ssp[i]);}
					   
				    }
				    
				   for(int i=0;i<(ssp.length)/2;i++)
				   {
					   for(int j=0;j<2;j++)
					   {
						   expected_path_index[i][j]=houzi_int[num1234[i][j]];
					   }
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
			for (int i=Expected_node_num;i>0;i--){
				Permute_num = Permute_num * i;
			}
			
			String[][] Permute_Matrix = new String[Permute_num][Expected_node_num];
			
			 arrange(expected_nodes_input, 0, expected_nodes_input.length,Permute_Matrix);//求排列遍历Permute_Matrix[i][j]
			 
		//===========================================生成虚拟图表对象=====================================	
			Graphs g = new Graphs();
			
			for(int i=0;i<mapping.length;i++){
				List vex_temp = new ArrayList();
				vex_temp.clear();

				for(int j=0;j<mapping.length;j++){
	
					if((adjMatrix[i][j]!=0)&&(adjMatrix[i][j]!=65535)){
						vex_temp.add(new Vertexs(mapping[j],adjMatrix[i][j]));
					}
				}
				int sizee=vex_temp.size();
				Vertexs[] haha=(Vertexs[])vex_temp.toArray(new Vertexs[sizee] );
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
			
			
			
			for (int i=0;i<Permute_num;i++){
				
				for(int j=0;j<Expected_node_num;j++)
				{
					expected_nodes_input[j]=Permute_Matrix[i][j];//数组存放输入的必过节点
				}
				
				List temp=Expected_Points_Short_Path_Print(list_mapping, g, adjMatrix,"S",expected_nodes_input , "E");//满足约束3
				
				Object[] b = temp.toArray(new String[temp.size()]);//list转数组
				String[] c=(String[]) b;
				int num=c.length;
				int flag[]=new int[expected_path_index.length];
				for(int mi=0;mi<expected_path_index.length;mi++)
				{
					flag[mi]=0;
				}
				
					//===================查询是否过指定路径====================================
				for(int x=0;x<num-1;x++){
					
					for(int mi=0;mi<expected_path_index.length;mi++){
						
						if( (c[x]==expected_path_index[mi][0] && c[x+1]==expected_path_index[mi][1])||(c[x]==expected_path_index[mi][1] && c[x+1]==expected_path_index[mi][0])  ){
						flag[mi]=1;
						
						}
					}		
				}
				
				int sum_flag=0;
				for(int u=0;u<expected_path_index.length;u++){
					sum_flag=sum_flag+flag[u];
				}
				
				if(sum_flag==expected_path_index.length){
					counts++;
					if( min>=Short_Distance(list_mapping,adjMatrix, temp) ){
						
						if(min>Short_Distance(list_mapping,adjMatrix, temp)){
							min=Short_Distance(list_mapping,adjMatrix, temp);
							min_list.clear();
							min_list.addAll(temp);
							min_distance_nodes=temp.size();
							min_list.add(String.valueOf(min_distance_nodes));
							if(Compare_min_nodes>min_distance_nodes){Compare_min_nodes=min_distance_nodes;}
						}else{
							min=Short_Distance(list_mapping,adjMatrix, temp);
							min_list.addAll(temp);
							min_distance_nodes=temp.size();
							min_list.add(String.valueOf(min_distance_nodes));
							if(Compare_min_nodes>min_distance_nodes){Compare_min_nodes=min_distance_nodes;}
						}
					}
					
					if(min_nodes>=temp.size()){
						if(min_nodes>temp.size()){
							min_nodes=temp.size();
							min_nodes_list.clear();
							min_nodes_list.addAll(temp);
							min_nodes_distance=Short_Distance(list_mapping,adjMatrix, temp);
						}else{
							min_nodes=temp.size();
							min_nodes_list.addAll(temp);
							min_nodes_distance=Short_Distance(list_mapping,adjMatrix, temp);
						}
						
					}
					
				}
				
				
			}//for
			
			
			// 话费到点数  话费最低，有几组路径，考虑点数最少，if (min)
			System.out.println("***********************************************************");
			System.out.println("***********************   分析数据报告     ****************");
			System.out.println("***********************************************************");
			System.out.println();
				System.out.println("****=========== 满足3，4，5约束的路径有"+counts +"种");
			if(Compare_min_nodes>constraints)
			{
				System.out.println("****==============   最短路径节点数大于"+constraints+"   ");
				System.out.println("****==============         无解                   ");
				System.out.println("****==============       参考解如下:                ");
				System.out.println("****==============       满足约束3,4,5前提下                ");
				int sizel = min_list.size();  
				List<String> output_path=new ArrayList<String>(); 
				String[] temp_arr = (String[])min_list.toArray(new String[sizel]);
				for(int i=0;i<sizel;i++)
				{
					if(temp_arr[i]=="S")
					{
						temp_index.add(i);
					}
					if(temp_arr[i]=="E")
					{
						temp_index.add(i);
					}
				}
				for(int i=0;i<(temp_index.size())/2;i++)
				{
					output_path.add("S");
					for(int j=1;j<temp_index.get(i*2+1)-temp_index.get(i*2);j++)
					{
						output_path.add(temp_arr[temp_index.get(i*2)+j]);
					}
					output_path.add("E");
					System.out.println("花费最优路径="+output_path);
					System.out.println("对应路径的节点数目="+temp_arr[temp_index.get(i*2)+temp_index.get(i*2+1)-temp_index.get(i*2)+1]);
					output_path.clear();
				}
				//System.out.println("花费最优路径="+min_list);
				System.out.println("花费最优花费="+min);
				//System.out.println("花费最少通过的节点数="+min_distance_nodes);
				System.out.println();
				
				
				temp_index.clear();
				 sizel = min_nodes_list.size();  
				 String[] output_path2=new String[min_nodes]; 
				 String[] temp_arr2 = (String[])min_nodes_list.toArray(new String[sizel]);
				for(int i=0;i<sizel;i++)
				{
					if(temp_arr2[i]=="S")
					{
						temp_index.add(i);
					}
				}
				for(int i=0;i<temp_index.size();i++)
				{
					for(int j=0;j<min_nodes;j++)
					{
						output_path2[j]=temp_arr2[temp_index.get(i)+j];
					}
					System.out.println("点数最优路径="+Arrays.toString(output_path2));
				}
				//System.out.println("点数最优路径="+min_nodes_list);
				System.out.println("点数最优花费="+min_nodes_distance);
				System.out.println("点数最优通过的节点数="+min_nodes);
			}
			else
			{
				System.out.println("****==============        最优解如下                   ===============****");
				int sizel = min_list.size();  
				List output_path=new ArrayList<>(); 
				String[] temp_arr = (String[])min_list.toArray(new String[sizel]);
				for(int i=0;i<sizel;i++)
				{
					if(temp_arr[i]=="S")
					{
						temp_index.add(i);
					}
					if(temp_arr[i]=="E")
					{
						temp_index.add(i);
					}
				}
				/*for(int i=0;i<(temp_index.size())/2;i++)
				{
					System.out.println("对应路径的节点数目="+temp_arr[temp_index.get(i*2)+temp_index.get(i*2+1)-temp_index.get(i*2)+1]);
				}*/
				
				for(int i=0;i<(temp_index.size())/2;i++)
				{
					output_path.add("S");
					for(int j=1;j<temp_index.get(i*2+1)-temp_index.get(i*2);j++)
					{
						output_path.add(temp_arr[temp_index.get(i*2)+j]);
					}
					
					output_path.add("E");
					if(constraints>=Integer.parseInt(temp_arr[temp_index.get(i*2)+temp_index.get(i*2+1)-temp_index.get(i*2)+1]))
					{
						System.out.println("花费最优路径="+output_path);
						System.out.println("对应路径的节点数目="+temp_arr[temp_index.get(i*2)+temp_index.get(i*2+1)-temp_index.get(i*2)+1]);
						
					}
					
					/*System.out.println("花费最优路径="+output_path);
					System.out.println("对应路径的节点数目="+temp_arr[temp_index.get(i*2)+temp_index.get(i*2+1)-temp_index.get(i*2)+1]);*/
					output_path.clear();
				}
				//System.out.println("花费最优路径="+min_list);
				System.out.println("花费最优花费="+min);
			
				/*System.out.println("最优路径="+min_list);
				System.out.println("最优花费="+min);*/
				
			}
			
			System.out.println("==============》》》》分析结束");
			while(true){;}
		}



		
		//重载
		
		
		
		
		private static List Expected_Points_Short_Path_Print(List<String> list_mapping, Graphs g, int[][] adjMatrix,String Point1,String[] expected_nodes_input,String Point8) {
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

	class Vertexs implements Comparable<Vertexs> {
		
		private String id;
		private Integer distance;
		
		public Vertexs(String id, Integer distance) {
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
			Vertexs other = (Vertexs) obj;
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
		public int compareTo(Vertexs o) {
			if (this.distance < o.distance)
				return -1;
			else if (this.distance > o.distance)
				return 1;
			else
				return this.getId().compareTo(o.getId());
		}
		
	}

	class Graphs {
		
		private final Map<String, List<Vertexs>> vertices;
		
		public Graphs() {
			this.vertices = new HashMap<String, List<Vertexs>>();
		}
		
		public void addVertex(String character, List<Vertexs> vertex) {
			this.vertices.put(character, vertex);
		}
		 
		@Override
		public String toString() {
			return "Graph [vertices=" + vertices + "]";
		}

		public List<String> getShortestPath(String start, String finish) {
			final Map<String, Integer> distances = new HashMap<String, Integer>();
			final Map<String, Vertexs> previous = new HashMap<String, Vertexs>();
			PriorityQueue<Vertexs> nodes = new PriorityQueue<Vertexs>();
			
			for(String vertex : vertices.keySet()) {
				if (vertex == start) {
					distances.put(vertex, 0);
					nodes.add(new Vertexs(vertex, 0));
					//测试
					//System.out.println("vertex="+vertex);
					//
				} else {
					distances.put(vertex, Integer.MAX_VALUE);
					nodes.add(new Vertexs(vertex, Integer.MAX_VALUE));
				}
				//测试
				//System.out.println("vertex="+vertex);
				//
				previous.put(vertex, null);
			}
			
			while (!nodes.isEmpty()) {
				Vertexs smallest = nodes.poll();
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
							
				for (Vertexs neighbor : vertices.get(smallest.getId())) {
					Integer alt = distances.get(smallest.getId()) + neighbor.getDistance();
					if (alt < distances.get(neighbor.getId())) {
						distances.put(neighbor.getId(), alt);
						previous.put(neighbor.getId(), smallest);
						
						forloop:
						for(Vertexs n : nodes) {
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


/*	class MutliThread extends Thread{
	    private int ticket=100;//每个线程都拥有100张票
	    MutliThread(String name){
	        super(name);//调用父类带参数的构造方法
	    }
	    public void run(){
for (int i=0;i<Permute_num;i++){
				
				for(int j=0;j<Expected_node_num;j++)
				{
					expected_nodes_input[j]=Permute_Matrix[i][j];
				}
				List<String> test = g.getShortestPath("S", "N2");
				List<String> test2 = g.getShortestPath("32123", "645645516");
				System.out.println("test="+test);
				System.out.println("test2="+test2);
				List temp=Expected_Points_Short_Path_Print(list_mapping, g, adjMatrix,"S",expected_nodes_input , "E");
				Object[] b = temp.toArray(new String[temp.size()]);
				String[] c=(String[]) b;
				int num=c.length;
				int flag[]=new int[expected_path_index.length];
				for(int mi=0;mi<expected_path_index.length;mi++)
				{
					flag[mi]=0;
				}
				

				for(int x=0;x<num-1;x++){
					
					for(int mi=0;mi<expected_path_index.length;mi++){
						
						if( (c[x]==expected_path_index[mi][0] && c[x+1]==expected_path_index[mi][1])||(c[x]==expected_path_index[mi][1] && c[x+1]==expected_path_index[mi][0])  ){
						flag[mi]=1;
						
						}
					}
					
					
					
				}
				int sum_flag=0;
				for(int u=0;u<expected_path_index.length;u++){
					sum_flag=sum_flag+flag[u];
				}
				if(sum_flag==expected_path_index.length){
					counts++;
					if( min>=Short_Distance(list_mapping,adjMatrix, temp) ){
						
						if(min>Short_Distance(list_mapping,adjMatrix, temp)){
							min=Short_Distance(list_mapping,adjMatrix, temp);
							min_list.clear();
							min_list.addAll(temp);
							min_distance_nodes=temp.size();
						}else{
							min=Short_Distance(list_mapping,adjMatrix, temp);
							min_list.addAll(temp);
							min_distance_nodes=temp.size();
						}
					}
					
					if(min_nodes>=temp.size()){
						if(min_nodes>temp.size()){
							min_nodes=temp.size();
							min_nodes_list.clear();
							min_nodes_list.addAll(temp);
							min_nodes_distance=Short_Distance(list_mapping,adjMatrix, temp);
						}else{
							min_nodes=temp.size();
							min_nodes_list.addAll(temp);
							min_nodes_distance=Short_Distance(list_mapping,adjMatrix, temp);
						}
						
					}
					
				}
				
				
			}//for
	    }
	}
*/


