package bean;

import java.util.List;

//具有相似度的路径部分
public class SimilarPath {
	private List<Node> nodes;
	//相似路径点个数
	private int simNum;

	public SimilarPath(List<Node> nodes, int simNum){
		this.nodes = nodes;
		this.simNum = simNum;
	}
	
	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public int getSimNum() {
		return simNum;
	}

	public void setSimNum(int simNum) {
		this.simNum = simNum;
	}
	
	
	
}
