int binarySearch(vector<int> v, int element)
{
    int l = 0;
    int h = v.size() - 1;
    while (l <= h)
    {
        int mid = l + (h - l) / 2;

        if (arr[mid] == element)
            return mid;
        if (mid >= l && arr[mid - 1] == element)
            return mid - 1;
        if (mid <= h && arr[mid + 1] == element)
            return mid + 1;

        if (arr[mid] > element)
            h = mid - 2;
        else
            l = mid + 2;
    }
    return -1;
}
