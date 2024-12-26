package Project.DSA;

import java.util.ArrayList;
import java.util.List;

public class FindDuplicates
{
	public List<Integer> findDuplicates(int[] arr)
	{
		List<Integer> dupEle = new ArrayList<>();

		for(int i = 0; i < arr.length; i++)
		{
			int element = arr[i];
			if(dupEle.contains(element))
			{
				continue;
			}
			else
			{
				for(int j = 0; j < arr.length; j++)
				{
					if(i == j)
					{
						continue;
					}
					else
					{
						int secElement = arr[j];
						if(element == secElement)
						{
							dupEle.add(element);
						}
					}
				}

			}
		}
		return dupEle;
	}

}
