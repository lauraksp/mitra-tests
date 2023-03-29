package com.mitraecp.api.mitratests.feature.cube;

import com.sun.prism.PixelFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.tree.TreeNode;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cube {

    private Integer id;
    private String name;
    private String dataType;
    private List<TreeNode> treeNodes;
    private String cubeGroup;
    private Integer size;

}
