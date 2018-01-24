
    // A
    this.contents.add(0, t); // enqueue
    return this.contents.remove(this.contents.size() - 1); // dequeue

    // B
    this.contents.add(0, t); // enqueue
    return this.contents.remove(0); // dequeue

    // C
    this.contents.add(this.contents.size() - 1, t); // enqueue
    return this.contents.remove(0); // dequeue

    // D
    this.contents.add(this.contents.size() - 1, t); // enqueue
    return this.contents.remove(this.contents.size() - 1); // dequeue

