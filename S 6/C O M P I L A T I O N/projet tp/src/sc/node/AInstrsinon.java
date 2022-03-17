/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AInstrsinon extends PInstrsinon
{
    private TSinon _sinon_;
    private PInstrbloc _instrbloc_;

    public AInstrsinon()
    {
        // Constructor
    }

    public AInstrsinon(
        @SuppressWarnings("hiding") TSinon _sinon_,
        @SuppressWarnings("hiding") PInstrbloc _instrbloc_)
    {
        // Constructor
        setSinon(_sinon_);

        setInstrbloc(_instrbloc_);

    }

    @Override
    public Object clone()
    {
        return new AInstrsinon(
            cloneNode(this._sinon_),
            cloneNode(this._instrbloc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInstrsinon(this);
    }

    public TSinon getSinon()
    {
        return this._sinon_;
    }

    public void setSinon(TSinon node)
    {
        if(this._sinon_ != null)
        {
            this._sinon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._sinon_ = node;
    }

    public PInstrbloc getInstrbloc()
    {
        return this._instrbloc_;
    }

    public void setInstrbloc(PInstrbloc node)
    {
        if(this._instrbloc_ != null)
        {
            this._instrbloc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._instrbloc_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._sinon_)
            + toString(this._instrbloc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._sinon_ == child)
        {
            this._sinon_ = null;
            return;
        }

        if(this._instrbloc_ == child)
        {
            this._instrbloc_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._sinon_ == oldChild)
        {
            setSinon((TSinon) newChild);
            return;
        }

        if(this._instrbloc_ == oldChild)
        {
            setInstrbloc((PInstrbloc) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
