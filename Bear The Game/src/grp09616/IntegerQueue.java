package grp09616;

public class IntegerQueue
{
	private IQueueElement first;
	private int length;

	public IntegerQueue()
	{
		first = null;
		length = 0;
	}

	public void append(int val)
	{
		if (first == null)
		{
			first = new IQueueElement(val);
		} else
		{
			first.append(val);
		}
		length++;
	}

	public int poll()
	{
		if (first != null)
		{
			int output = first.getVal();
			first = first.getNext();
			length--;
			return output;
		}
		return -1;
	}

	public int peek()
	{
		if (first != null)
		{
			return first.getVal();
		}
		return -1;
	}

	public int[] view()
	{
		int[] output = new int[length];
		IQueueElement current = first;
		for (int i = 0; i < length; i++)
		{
			output[i] = current.getVal();
			current = current.getNext();
		}
		return output;
	}

	public int length()
	{
		return length;
	}

	public class IQueueElement
	{
		private IQueueElement next;
		private int value;

		public IQueueElement(int v)
		{
			next = null;
			setVal(v);
		}

		public void append(int val)
		{
			if (next == null)
			{
				next = new IQueueElement(val);
			} else
			{
				next.append(val);
			}
		}

		public IQueueElement getNext()
		{
			return next;
		}

		public void setVal(int v)
		{
			value = v;
		}

		public int getVal()
		{
			return value;
		}
	}
}
