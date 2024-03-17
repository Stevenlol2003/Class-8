def interval_scheduling(jobs):
    n = len(jobs)
    jobs.sort(key=lambda x: x[1])
    count = 0
    end = -1
    for i in range(n):
        if jobs[i][0] >= end:
            count += 1
            end = jobs[i][1]
    return count

def main():
    t = int(input().strip())
    for _ in range(t):
        n = int(input().strip())
        jobs = []
        for i in range(n):
            s, e = map(int, input().strip().split())
            jobs.append((s, e))
        print(interval_scheduling(jobs))

if __name__ == '__main__':
    main()

