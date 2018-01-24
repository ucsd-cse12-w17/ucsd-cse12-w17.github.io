
    // A  (works)
    this.contents.add(0, t); // enqueue
    return this.contents.remove(this.contents.size() - 1); // dequeue

    // B  (not a queue)
    this.contents.add(0, t); // enqueue
    return this.contents.remove(0); // dequeue

    // C (has off-by-one in enqueue)
    this.contents.add(this.contents.size() - 1, t); // enqueue
    return this.contents.remove(0); // dequeue

    // D  (not a queue)
    this.contents.add(this.contents.size() - 1, t); // enqueue
    return this.contents.remove(this.contents.size() - 1); // dequeue

