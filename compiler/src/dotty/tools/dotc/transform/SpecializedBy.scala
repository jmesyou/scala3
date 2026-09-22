package dotty.tools.dotc.transform

import dotty.tools.dotc.transform.MegaPhase.MiniPhase
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.ast.tpd
import dotty.tools.dotc.core.Contexts.ctx
import dotty.tools.dotc.core.Symbols.defn
import dotty.tools.dotc.ast.Trees.Tree
import dotty.tools.dotc.ast.tpd.DefDef
import dotty.tools.dotc.core.Flags

class SpecializedBy extends MiniPhase:
  
  override def phaseName: String = "specializedBy"
  
  override def changesMembers: Boolean = true
  
  override def transformTemplate(tree: tpd.Template)(using Context): tpd.Tree = {
    val owner = ctx.owner
    ctx.owner.getAnnotation(defn.SpecializedByAnnot) match
      case None => tree
      case Some(annot) => 
        val specialization = annot.argumentTypes.head
        
        val candidates = tree.body.filter {
          member => member match
            case ddef: DefDef => ddef.tpt.tpe == owner.info
            case _ => false
            
          
        }    
        ???
  }
  
  
  