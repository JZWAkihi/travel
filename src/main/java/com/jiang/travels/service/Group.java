package com.jiang.travels.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;



public class Group{
    //存储顶点集合
    private ArrayList<String> verexList;

    //存储图对应的邻接矩阵
    private int[][] edges;

    //表示边的数目
    private int numofEdges;

    //构造器
    public Group(int n){
        //初始化矩阵和vertexList
        edges = new int[n][n];
        verexList = new ArrayList<String>(n);
        numofEdges = 0;
    }

    //插入节点
    public void insertVertex(String vertex){
        verexList.add(vertex);
    }


    //添加边
    public void insertEdges(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numofEdges++;
    }


    //返回节点的个数
    public int getNumofVertex(){
        return verexList.size();

    }

    //返回边个数
    public int getNumofEdges(){
        return numofEdges;
    }

    //返回结点i对应的数据
    public String getValueByIndex(int i){
        return verexList.get(i);
    }

    //返回v1,v2的权值
    public int getWight(int v1,int v2){
        return edges[v1][v2];
    }


    //显示图对应的矩阵
    public void showGraph(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }


    public static void main(String[] args){
        int n = 5;
        String VertexValue[] = {"A","B","C","D","E"};
        Group group = new Group(n);

        for(String value : VertexValue){
            group.insertVertex(value);
        }

        group.insertEdges(0,1,1);
        group.insertEdges(0,2,1);
        group.insertEdges(1,2,1);
        group.insertEdges(1,3,1);
        group.insertEdges(1,4,1);

        group.showGraph();
    }
}